//package kg.attractor.jobsearch.controller;
//
//import kg.attractor.jobsearch.service.ChatService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ChatWebSocketController {
//
//    private final SimpMessagingTemplate messagingTemplate;
//    private final ChatService chatService;
//
//    @Autowired
//    public ChatWebSocketController(SimpMessagingTemplate messagingTemplate, ChatService chatService) {
//        this.messagingTemplate = messagingTemplate;
//        this.chatService = chatService;
//    }
//
//    @MessageMapping("/chat/{chatId}/send")
//    public void sendMessage(@DestinationVariable Long chatId, ChatMessageDto message) {
//        // Сохраняем сообщение в базе данных
//        ChatMessageDto savedMessage = chatService.saveMessage(message);
//
//        // Отправляем сообщение всем подписанным на этот чат
//        messagingTemplate.convertAndSend("/topic/chat/" + chatId, savedMessage);
//    }
//}