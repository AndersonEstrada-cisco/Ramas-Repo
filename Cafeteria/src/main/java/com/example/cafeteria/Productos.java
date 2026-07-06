package com.example.cafeteria;
 public class Productos
 {
   private String nombre;
   private double precio;
   private int id;
   private String tipoCafe;

   public Productos() {}

   public Productos(String nombre, double precio, int id, String tipoCafe) {
     this.nombre = nombre;
     this.precio = precio;
     this.id = id;
     this.tipoCafe = tipoCafe;
   }

   public String getNombre() {
/* 20 */     return this.nombre;
/*    */   }

   public void setNombre(String nombre) {
/* 24 */     this.nombre = nombre;
/*    */   }

   public double getPrecio() {
/* 28 */     return this.precio;
/*    */   }

   public void setPrecio(double precio) {
/* 32 */     this.precio = precio;
/*    */   }

   public int getId() {
/* 36 */     return this.id;
/*    */   }

   public void setId(int id) {
/* 40 */     this.id = id;
/*    */   }

   public String getTipoCafe() {
/* 44 */     return this.tipoCafe;
/*    */   }

   public void setTipoCafe(String tipoCafe) {
/* 48 */     this.tipoCafe = tipoCafe;
/*    */   }
 }


