package com.flightradar.flightradar.service.friends;

import com.flightradar.flightradar.model.friends.Friendship;
import com.flightradar.flightradar.repository.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.flightradar.flightradar.service.Static.CurrentUser;

@Service
@Component
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    boolean isUserLoggedIn() {

        String logged = "anonymousUser";

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(logged)) {

            return false;
        }
        return true;
    }

    public Integer friendsNotification() {

        if (isUserLoggedIn()) {

            List<Friendship> friendshipList = friendsRepository.findByRequester(CurrentUser().getUser());

            List<Integer> notifications = new LinkedList<>();
            for (int i = 0; i < friendshipList.size(); i++) {
                if (friendshipList.get(i).isPending()) {
                    notifications.add(i);
                }

            }
            System.out.println("Notification test" + notifications.size() + 1);
            return notifications.size();
        }
        return 0;
    }

}


