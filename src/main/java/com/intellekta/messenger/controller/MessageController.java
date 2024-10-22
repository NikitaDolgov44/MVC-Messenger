package com.intellekta.messenger.controller;

import com.intellekta.messenger.entity.Message;
import com.intellekta.messenger.entity.User;
import com.intellekta.messenger.repository.MessageRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PostMapping("/updateNickname")
    public String updateNickname(@RequestParam("newNickname") String newNickname, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            loggedInUser.setNickname(newNickname); // Обновляем никнейм пользователя
            session.setAttribute("loggedInUser", loggedInUser); // Сохраняем обновленного пользователя в сессии
        }
        return "redirect:/messages"; // Перенаправляем на страницу сообщений
    }



    @GetMapping("/home")
    public String showHomePage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login"; // Если пользователь не авторизован, перенаправляем на страницу логина
        }
        model.addAttribute("loggedInUser", loggedInUser);
        return "home"; // Возвращаем шаблон home.html
    }

    @GetMapping("/messages")
    public String showMessagesPage(Model model, HttpSession session,
                                   @RequestParam(value = "filterName", required = false) String filterName) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login"; // Redirect to login if not authorized
        }

        model.addAttribute("loggedInUser", loggedInUser); // Передаем обновленного пользователя в модель

        // Если указан фильтр, показываем сообщения от указанного пользователя
        List<Message> messages;
        if (filterName != null && !filterName.isEmpty()) {
            messages = messageRepository.findBySenderName(filterName);
        } else {
            messages = messageRepository.findAllByOrderBySentAtDesc();
        }
        model.addAttribute("messages", messages);
        return "messages"; // Show messages page
    }

    @PostMapping("/send")
    public String addMessage(@RequestParam("text") String text, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login"; // Redirect to login if not authorized
        }

        // Create a new message
        Message message = new Message();
        message.setText(text);
        message.setSenderName(loggedInUser.getNickname() != null ? loggedInUser.getNickname() : loggedInUser.getUsername());
        message.setSentAt(new Date());
        messageRepository.save(message);

        return "redirect:/messages"; // Redirect to messages page
    }

    @GetMapping("/clearSession")
    public String clearSession(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Clear session
        return "redirect:/";
    }
}





