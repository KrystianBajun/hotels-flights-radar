package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.blog.Blog;
import com.flightradar.flightradar.repository.BlogRepository;
import com.flightradar.flightradar.security.AllowedForModerator;
import com.flightradar.flightradar.util.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping(value = "article/{id}", method = RequestMethod.GET)
    public String blogShow(@PathVariable("id") long id, Model model) {

        Blog blog = blogRepository.findById(id);
        model.addAttribute("blog", blog);
        return "blog/article";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @AllowedForModerator
    public String manageArticles(Model model) {

        model.addAttribute("articles", blogRepository.findAll());
        return "blog/article-list";
    }

    @RequestMapping(value = "{id}/remove", method = RequestMethod.GET)
    @AllowedForModerator
    public void remove(@PathVariable("id") long id) {

        Blog article = blogRepository.findById(id);
        if (article == null) {
            throw new ArticleNotFoundException();
        }
        blogRepository.delete(article);
    }

    @RequestMapping(value = "article/new", method = RequestMethod.GET)
    @AllowedForModerator
    public String newArticle(Model model) {

        model.addAttribute("blog", new Blog());
        return "blog/article-new";
    }

    @RequestMapping(value = "article/new/save", method = RequestMethod.POST)
    public String saveNewArticle(@Valid Blog blog, BindingResult result) {

        if (result.hasErrors()) {
            return "article/new";
        }
        blog.setStatus(true);
        blogRepository.save(blog);
        return "article/list";
    }

    @RequestMapping(value = "article/edit/{id}", method = RequestMethod.GET)
    public String articleUpdate(@PathVariable("id") long id, Model model) {

        Blog blog = blogRepository.findById(id);
        model.addAttribute("blog", blog);

        return "blog/article-update";
    }

    @RequestMapping(value = "article/update/{id}", method = RequestMethod.POST)
    public String updateArticle(@PathVariable("id") long id, @Valid Blog blog, BindingResult result) {
        if (result.hasErrors()) {
            blog.setId(id);
            return "article/edit/{id}";
        }

        blogRepository.save(blog);
        return "redirect:/blog/list";
    }

    @RequestMapping(value = "{id}/upload", method = RequestMethod.GET)
    @AllowedForModerator
    public String uploadFormImg(@PathVariable("id") long id, Model model) {
        Blog blog = blogRepository.findById(id);
        model.addAttribute("blog", blog);
        return "blog/image-upload";
    }

    @RequestMapping(value = "{id}/upload", method = RequestMethod.POST)
    @AllowedForModerator
    public String uploadImage(@PathVariable("id") long id, @RequestParam("files") MultipartFile files[]) {

        if (files.length > 0) {
            for (MultipartFile file : files) {
                try {
                    byte[] bytes = file.getBytes();
                    String path = "src/main/resources/static/img/" + file.getOriginalFilename();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(path)));
                    stream.write(bytes);
                    stream.close();

                    Blog blog = blogRepository.findById(id);
                    blog.setImage(file.getOriginalFilename());
                    blogRepository.save(blog);

                } catch (Exception e) {
                    System.out.println("Problem with : " + e);
                }
            }
            return "redirect:/blog/list";
        }
        return "/";
    }


}
