package com.example.cafeteria;

//Mi parte del trabajo fueron las validaciones
 public class Validaciones
 {
   public static boolean campoVacio(String texto) {
/*  6 */     return (texto == null || texto.trim().isEmpty());
/*    */   }

   public static boolean esNumero(String texto) {
     if (campoVacio(texto)) {
       return false;
     }

     try {
       Double.parseDouble(texto);
       return true;
     } catch (NumberFormatException e) {
       return false;
     }
   }

   public static boolean precioValido(double precio) {
/* 23 */     return (precio > 0.0D);
/*    */   }

   public static boolean comboSeleccionado(String valor) {
/* 27 */     return (valor != null && !valor.trim().isEmpty());
/*    */   }

   public static boolean nombreValido(String nombre) {
     return (nombre != null && nombre
       .trim().length() >= 2 && nombre
       .trim().length() <= 50);
   }
 }


