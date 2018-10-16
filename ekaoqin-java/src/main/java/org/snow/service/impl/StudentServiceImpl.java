package org.snow.service.impl;


import com.rabbitmq.client.*;
import org.snow.dao.jpa.ClaxxRepository;
import org.snow.dao.jpa.RoomRepository;
import org.snow.dao.jpa.StudentRepository;
import org.snow.form.StudentRespond;
import org.snow.model.business.Student;

import org.snow.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.*;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    private final static String QUEUE_NAME = "amq_sync_xhz";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ClaxxRepository claxxRepository;


    @Override
    public List<StudentRespond> getAllStudents() {

        Iterable<Student> geted = studentRepository.findAll();
        List<StudentRespond> list = new ArrayList<StudentRespond>();
        geted.forEach(single -> {
            if (single.getIsDeleted() == null || single.getIsDeleted() == false) {
                StudentRespond studentRespond = new StudentRespond();
                BeanUtils.copyProperties(single, studentRespond);
                if (single.getClassId() != null) {
                    studentRespond.setClaxxName(claxxRepository.findById(single.getClassId()).get().getName());
                }
                if (single.getRoomId() != null) {
                    studentRespond.setRoomName(roomRepository.findById(single.getRoomId()).get().getName());
                }
                list.add(studentRespond);
            }
        });
        return list;
    }

    @Override
    public Boolean addStudent(Student student) {
        studentRepository.save(student);
        return true;
    }

    @Override
    public Boolean updateStudentById(Long studentId, Student student) {
        student.setId(studentId);
        studentRepository.save(student);
        return true;
    }

    @Override
    public Boolean deleteStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        student.get().setIsDeleted(true);
        studentRepository.save(student.get());
        return true;
    }

    @Override
    public Boolean updateStatusByMq() throws IOException {

        Connection connection = initMq();
        final Channel channel = connection.createChannel();
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("x-expires", 86400000);
        channel.queueDeclare(QUEUE_NAME, true, false, false, arguments);
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                try {
                    String message = new String(body, "UTF-8");
                    System.out.println(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer);
        return null;
    }

    public static Connection initMq() {
        ConnectionFactory factory = null;
        Connection connection = null;
        try {
            factory = new ConnectionFactory();
            //ip
            factory.setHost("10.30.23.252");
            factory.setPort(5673);// MQ端口
            factory.setUsername("admin");// MQ用户名
            factory.setPassword("123456");// MQ密码

            connection = factory.newConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
