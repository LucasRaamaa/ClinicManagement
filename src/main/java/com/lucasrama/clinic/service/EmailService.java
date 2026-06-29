package com.lucasrama.clinic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendAppointmentConfirmation(String toEmail, String patientName, String doctorName, String date, String time) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("TU_CORREO@gmail.com"); // Debe coincidir con el application.properties
      message.setTo(toEmail);
      message.setSubject("Confirmación de Turno - HealthTech Clinic");
      message.setText("Hola " + patientName + ",\n\n"
          + "Tu turno ha sido registrado exitosamente.\n"
          + "Médico: Dr/a. " + doctorName + "\n"
          + "Fecha: " + date + "\n"
          + "Hora: " + time + "\n\n"
          + "Gracias por utilizar nuestra plataforma.");

      mailSender.send(message);
      System.out.println("Correo de confirmación enviado exitosamente a: " + toEmail);

    } catch (Exception e) {
      // creamos una solucion momentanea
      // para que no tenga que colocar un email directamente en la app
      System.err.println("--- SIMULACIÓN ---");
      System.err.println("El turno se guardó, pero el correo no se pudo enviar debido a credenciales de prueba.");
      System.err.println("Intento de envío a: " + toEmail);
      System.err.println("------------------");
    }
  }
}
