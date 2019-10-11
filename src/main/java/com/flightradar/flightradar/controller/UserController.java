package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.comments.Messages;
import com.flightradar.flightradar.model.friends.Friendship;
import com.flightradar.flightradar.model.trip.flight.Flight;
import com.flightradar.flightradar.model.trip.hotel.HotelRating;
import com.flightradar.flightradar.model.user.*;
import com.flightradar.flightradar.repository.*;
import com.flightradar.flightradar.security.AllowedForAdmin;
import com.flightradar.flightradar.security.AllowedForUsers;
import com.flightradar.flightradar.security.SecurityConfiguration;
import com.flightradar.flightradar.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.flightradar.flightradar.service.Static.CurrentUser;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private
    UserRepository userRepository;
    @Autowired
    private
    FlightRepository flightRepository;
    @Autowired
    private
    AuthorityRepository authorityRepository;
    @Autowired
    private
    AuthenticationManager authenticationManager;
    @Autowired
    private
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private
    UserFinalTripRepository userFinalTripRepository;
    @Autowired
    private
    FriendsRepository friendsRepository;
    @Autowired
    private
    HotelRatingRepository hotelRatingRepository;
    @Autowired
    private
    UserProfileRepository userProfileRepository;
    @Autowired
    private
    PostsRepository postsRepository;

    @Autowired
    private
    MessagesRepository messagesRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute User user) {
        try {
            Authority authority = authorityRepository.findByRole("ROLE_USER");
            user.setAuthority(authority);
            String pass = user.getPassword();
            user.setPassword(SecurityConfiguration.encoder.encode(user.getPassword()));
            System.out.println(user.getName());
            userRepository.save(user);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, pass, userDetails.getAuthorities());
            authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "redirect:my/profile/edit";

        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "Index";
    }

    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    @AllowedForUsers
    public String profile(@PathVariable("username") String username, Model model) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        List<Posts> posts = postsRepository.findByUser(user);

        model.addAttribute("users", user);
        model.addAttribute("posts", posts);
        return "users/user-profile";
    }

    @RequestMapping(value = "/add/post", method = RequestMethod.GET)
    @AllowedForUsers
    public String addPost(Model model) {

        User user = userRepository.findByUsername(CurrentUser().getUsername());
        model.addAttribute("post", new Posts());
        model.addAttribute("user", user);
        return "users/user-add-post";
    }

    @RequestMapping(value = "add/post/{id}", method = RequestMethod.POST)
    public String addComment(@PathVariable("id") long id, @Valid Posts posts, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "/new/comment/{id}";
        }
        User user = userRepository.findById(id);
        if (user == null) {

            throw new UserNotFoundException();
        }
        posts.setDate(new Date());
        posts.setUser(user);

        postsRepository.save(posts);

        redirectAttributes.addAttribute("username", user.getUsername());

        return "redirect:/users/profile/{username}";
    }

    @AllowedForUsers
    @RequestMapping(value = "my/profile/edit", method = RequestMethod.GET)
    public String myProfile(Model model) {
        model.addAttribute(model.addAttribute("profile", new UserProfile()));
        return "users/user-profile-edit";
    }

    @AllowedForUsers
    @RequestMapping(value = "my/profile/update", method = RequestMethod.POST)
    public String updateUserProfil(@Valid UserProfile userProfile, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/users/my/profile/edit";
        }
        User user = userRepository.findByUsername(CurrentUser().getUser().getUsername());
        userProfile.setUser(user);
        user.setUserProfile(userProfile);
        userRepository.save(user);

        return "redirect:/users/my/profile/edit";
    }

    @RequestMapping(value = "my/profile/edit/avatar", method = RequestMethod.GET)
    @AllowedForUsers
    public String uploadFormImg(Model model) {
        User user = userRepository.findByUsername(CurrentUser().getUser().getUsername());
        model.addAttribute("user", user);
        return "users/avatar-upload";
    }

    @RequestMapping(value = "my/profile/edit/avatar/upload", method = RequestMethod.POST)
    @AllowedForUsers
    public String uploadImage(@RequestParam("files") MultipartFile files[]) {

        if (files.length > 0) {
            for (MultipartFile file : files) {
                try {
                    byte[] bytes = file.getBytes();
                    String path = "src/main/resources/static/img/avatar/" + file.getOriginalFilename();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(path)));
                    stream.write(bytes);
                    stream.close();

                    User user = userRepository.findByUsername(CurrentUser().getUser().getUsername());
                    user.getUserProfile().setAvatar("avatar/" + file.getOriginalFilename());
                    userRepository.save(user);

                } catch (Exception e) {
                    System.out.println("Problem with : " + e);
                }
            }
            return "redirect:/users/my/profile/edit/avatar";
        }
        return "/";
    }

    @RequestMapping(value = "{id}/remove", method = RequestMethod.GET)
    @AllowedForAdmin
    public String remove(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        userRepository.delete(user);
        model.addAttribute("userRepository", userRepository.findAll());

        return "redirect:/";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(Model model) {
        List<HotelRating> hotels = hotelRatingRepository.findAllByOrderByAverageDesc();

        System.out.println(hotels.get(0).getAverage());
        System.out.println(hotels.get(1).getAverage());
        System.out.println(hotels.get(2).getAverage());
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @AllowedForUsers
    public String findUser(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        redirectAttributes.addAttribute("username", username);

        return "redirect:profile/{username}";
    }

    @RequestMapping(value = "/invite", method = RequestMethod.GET)
    @AllowedForUsers
    public String invite(@RequestParam("username") String username, Model model) {
        User userInvited = userRepository.findByUsername(username);
        User userInviting = CurrentUser().getUser();


        if (userInvited == null || userInvited.getId() == userInviting.getId()) {
            throw new UserNotFoundException();
        }
        Friendship isFriends = friendsRepository.findByInvitingAndRequester(userInviting, userInvited);

        if (isFriends == null) {
            Friendship request = new Friendship();
            request.setPending(true);
            request.setRequester(userInvited);
            request.setInviting(userInviting);
            userInvited.getFriends().add(request);
            userInvited.getFriendRequests().add(request);
            userRepository.save(userInvited);
            friendsRepository.save(request);

            model.addAttribute("nick", userInvited.getUsername());
        } else {
            if (isFriends.isPending()) {
                throw new UserAlreadyInvitedException();
            }
            if (isFriends.isAccepted()) {
                throw new UserIsAlreadyFriendException();
            }
        }

        return "users/user-invited";
    }

    @RequestMapping(value = "/friendship/invitations", method = RequestMethod.GET)
    @AllowedForUsers
    public String invitationsList(Model model) {
        List<Friendship> friendsList = friendsRepository.findByRequester(CurrentUser().getUser());
        List<Friendship> friendsInvitations = new LinkedList<>();

        for (Friendship f : friendsList)
            if (f.isPending()) {
                friendsInvitations.add(f);
            }

        if (friendsInvitations.size() == 0) {
            throw new InvitationNotFoundException();
        }
        model.addAttribute("invitations", friendsInvitations);

        return "users/invitations-list";
    }

    @RequestMapping(value = "/friendship/accept/{id}", method = RequestMethod.GET)
    @AllowedForUsers
    public String friendAccept(@PathVariable("id") long id) {

        Friendship invitation = friendsRepository.findById(id).orElse(null);

        if (invitation == null) {
            throw new InvitationNotFoundException();
        }
        invitation.setPending(false);
        invitation.setAccepted(true);
        friendsRepository.save(invitation);

        return "redirect:/";
    }

    @RequestMapping(value = "/friends/list", method = RequestMethod.GET)
    @AllowedForUsers
    public String friendsList(Model model) {

        if (friendsList().size() == 0) {
            throw new FriendsNotFoundException();
        }
        model.addAttribute("friends", friendsList());

        System.out.println(friendsList().size());
        return "users/friends/friends-list";
    }

    @RequestMapping(value = "/friends/chat/{id}", method = RequestMethod.GET)
    @AllowedForUsers
    public String chat(@PathVariable("id") long id, Model model) {
        User userChat = userRepository.findById(id);

        if (!friendsList().contains(userChat)) {
            throw new UserNotFriendException();
        }
        List<Messages> userMessages = messagesRepository.findByUser(CurrentUser().getUser());
        List<Messages> actualChat = new ArrayList<>();

        for (Messages mes : userMessages) {
            if (mes.getSender().getId() == id || mes.getReceiver().getId() == id) {
                actualChat.add(mes);
            }
        }

        model.addAttribute("chat", actualChat);
        model.addAttribute("user", userChat);
        model.addAttribute("Messages", new Messages());

        return "chat/friends-chat";
    }

    @RequestMapping(value = "friends/chat/send/{id}", method = RequestMethod.POST)
    public String sendMessage(@PathVariable("id") long id, Messages messages, RedirectAttributes
            redirectAttributes) {
        User sender = CurrentUser().getUser();
        User receiver = userRepository.findById(id);

        messages.setDate(new Date());
        messages.setSender(sender);
        messages.setReceiver(receiver);
        messages.setUser(CurrentUser().getUser());

        messagesRepository.save(messages);
        messages.setUser(receiver);
        messagesRepository.save(messages);

        redirectAttributes.addAttribute("id", id);
        return "redirect:/users/friends/chat/{id}";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @AllowedForUsers
    public String flightsList(Model model) {
        List<Flight> flight = flightRepository.findByUserName(CurrentUser().getUsername());
        if (flight == null) {
            throw new FlightNotFoundException();
        }
        User user = userRepository.findById(CurrentUser().getUser().getId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        model.addAttribute("flight", flight);
        model.addAttribute("users", user);
        return "users/search-result";
    }

    private List friendsList() {
        User user = userRepository.findById(CurrentUser().getUser().getId());

        List<Friendship> friendshipList = friendsRepository.findByInvitingOrRequester(user, user);
        List<User> friendsAcceptedList = new LinkedList<>();
        for (Friendship friends : friendshipList) {
            if ((friends.isAccepted())) {
                if (!friends.getRequester().getUsername().equals(user.getUsername())) {
                    friendsAcceptedList.add(friends.getRequester());
                }
                if (!friends.getInviting().getUsername().equals(user.getUsername())) {
                    friendsAcceptedList.add(friends.getInviting());
                }
            }
        }
        return friendsAcceptedList;
    }


}