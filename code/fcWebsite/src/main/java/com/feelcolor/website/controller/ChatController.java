package com.feelcolor.website.controller;

import com.feelcolor.website.socket.TCPClient;
import com.feelcolor.website.socket.TCPServer;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("chat")
public class ChatController {
    @Resource
    private TCPServer tcpServer;
    @Resource
    private TCPClient tcpClient;

    @RequestMapping("/")
    @ApiOperation(value = "聊天室页面", httpMethod = "GET")
    public String chatView(){
        return "chat";
    }

    @RequestMapping("/startServer")
    @ResponseBody
    @ApiOperation(value = "开启聊天服务", httpMethod = "GET")
    public String startServer(){
        tcpServer.start();
        return "聊天室启动成功";
    }

    @RequestMapping("/joinServer")
    @ResponseBody
    @ApiOperation(value = "加入聊天服务", httpMethod = "GET")
    public String joinServer(String name){
        tcpClient.start(name);
        return "加入聊天室成功";
    }

    @RequestMapping("/sendMsgToServer")
    @ResponseBody
    @ApiOperation(value = "向服务器发送消息", httpMethod = "GET")
    public String sendMsgToServer(String msg){
        tcpClient.sendMsg(msg);
        return "加入聊天室成功";
    }

    @RequestMapping("/getServerStatus")
    @ResponseBody
    @ApiOperation(value = "向服务器发送消息", httpMethod = "GET")
    public String getServerStatus(String msg){
        tcpClient.sendMsg(msg);
        return "加入聊天室成功";
    }
}
