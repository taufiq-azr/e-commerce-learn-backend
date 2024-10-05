# E-Commerce Backend API

## Deskripsi

Proyek ini adalah sebuah API backend untuk aplikasi e-commerce yang dibangun menggunakan Java dan Spring Boot. API ini menyediakan endpoint untuk mengelola berbagai entitas seperti produk, pengguna, pesanan, ulasan, pengiriman, dan toko. Ini dibuat sebagai bahan pembelajaraan saya.

## Fitur

- Manajemen pengguna
- Manajemen produk
- Manajemen ulasan
- Manajemen pesanan
- Manajemen pengiriman
- Manajemen toko
- Mendapatkan data berdasarkan relasi antar entitas

## Struktur Proyek

Berikut adalah controller yang terdapat dalam proyek ini:

### 1. UserController

Mengelola semua operasi yang berkaitan dengan pengguna.

- **GET /api/v1/users**: Mendapatkan semua pengguna
- **GET /api/v1/users/{id}**: Mendapatkan pengguna berdasarkan ID
- **POST /api/v1/users**: Membuat pengguna baru
- **PUT /api/v1/users/{id}**: Mengupdate pengguna
- **DELETE /api/v1/users/{id}**: Menghapus pengguna

### 2. ShopController

Mengelola semua operasi yang berkaitan dengan toko.

- **GET /api/v1/shops**: Mendapatkan semua toko
- **GET /api/v1/shops/{id}**: Mendapatkan toko berdasarkan ID
- **GET /api/v1/shops/product/{productId}**: Mendapatkan toko berdasarkan ID produk
- **POST /api/v1/shops**: Membuat toko baru
- **PUT /api/v1/shops/{id}**: Mengupdate toko
- **DELETE /api/v1/shops/{id}**: Menghapus toko

### 3. ShipmentController

Mengelola semua operasi yang berkaitan dengan pengiriman.

- **GET /api/v1/shipments**: Mendapatkan semua pengiriman
- **GET /api/v1/shipments/{id}**: Mendapatkan pengiriman berdasarkan ID
- **GET /api/v1/shipments/order/{orderId}**: Mendapatkan pengiriman berdasarkan ID pesanan
- **POST /api/v1/shipments/order/{orderId}**: Membuat pengiriman baru
- **PUT /api/v1/shipments/{id}**: Mengupdate pengiriman
- **DELETE /api/v1/shipments/{id}**: Menghapus pengiriman

### 4. ReviewController

Mengelola semua operasi yang berkaitan dengan ulasan.

- **GET /api/v1/reviews**: Mendapatkan semua ulasan
- **GET /api/v1/reviews/{id}**: Mendapatkan ulasan berdasarkan ID
- **GET /api/v1/reviews/product/{productId}**: Mendapatkan ulasan berdasarkan ID produk
- **POST /api/v1/reviews/order/{orderId}**: Membuat ulasan baru
- **PUT /api/v1/reviews/{id}**: Mengupdate ulasan
- **DELETE /api/v1/reviews/{id}**: Menghapus ulasan

### 5. ProductController

Mengelola semua operasi yang berkaitan dengan produk.

- **GET /api/v1/products**: Mendapatkan semua produk
- **GET /api/v1/products/{id}**: Mendapatkan produk berdasarkan ID
- **GET /api/v1/products/category/{categoryId}**: Mendapatkan produk berdasarkan kategori
- **GET /api/v1/products/review/{reviewId}**: Mendapatkan produk berdasarkan ID ulasan
- **GET /api/v1/products/order/{orderId}**: Mendapatkan produk berdasarkan ID pesanan
- **GET /api/v1/products/shop/{shopId}**: Mendapatkan produk berdasarkan ID toko
- **POST /api/v1/products**: Membuat produk baru
- **PUT /api/v1/products/{id}**: Mengupdate produk
- **DELETE /api/v1/products/{id}**: Menghapus produk

### 6. OrderItemController

Mengelola semua operasi yang berkaitan dengan item pesanan.

- **GET /api/v1/order-items**: Mendapatkan semua item pesanan
- **GET /api/v1/order-items/{id}**: Mendapatkan item pesanan berdasarkan ID
- **POST /api/v1/order-items**: Membuat item pesanan baru
- **PUT /api/v1/order-items/{id}**: Mengupdate item pesanan
- **DELETE /api/v1/order-items/{id}**: Menghapus item pesanan

### 7. OrderController

Mengelola semua operasi yang berkaitan dengan pesanan.

- **GET /api/v1/orders**: Mendapatkan semua pesanan
- **GET /api/v1/orders/{id}**: Mendapatkan pesanan berdasarkan ID
- **POST /api/v1/orders**: Membuat pesanan baru
- **PUT /api/v1/orders/{id}**: Mengupdate pesanan
- **DELETE /api/v1/orders/{id}**: Menghapus pesanan

## Teknologi yang Digunakan

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (atau database lain sesuai konfigurasi)
- Maven

## Cara Menjalankan Proyek

1. Clone repositori ini ke lokal Anda.
2. Buka terminal dan arahkan ke direktori proyek.
3. Jalankan perintah berikut untuk menginstal dependensi:
   ```bash
   mvn clean install
   ```
4. Jalankan aplikasi:
   ```bash
   mvn spring-boot:run
   ```
5. API akan berjalan di `http://localhost:8080`. 

## Catatan

Pastikan untuk menyesuaikan file `application.properties` sesuai dengan kebutuhan database Anda.

## Lisensi

Proyek ini dilisensikan di bawah MIT License - lihat file LICENSE untuk detail lebih lanjut.
