# 📚 Library Management System

Sistem Manajemen Perpustakaan berbasis Web. Aplikasi ini memungkinkan pengelolaan data buku, anggota, serta peminjaman dan pengembalian buku secara efisien, baik untuk admin maupun anggota perpustakaan.

---

## 🚀 Fitur Utama

- 🔐 Login dan otentikasi pengguna
- 📖 Manajemen katalog buku
- 👤 Manajemen akun anggota
- 📥 Proses peminjaman & pengembalian
- 📊 Dashboard untuk admin & anggota
- 📄 Riwayat peminjaman
- ⚙️ Antarmuka frontend yang responsif (TailwindCSS)

---

## 🗂️ Struktur Proyek

ibrary_management_system/
├── BACKEND/ # Backend (Java)
│ ├── controllers/ # Logika aplikasi
│ ├── models/ # Model entitas
│ ├── config/ # Koneksi DB, konfigurasi
│ └── ...
├── FRONTEND/ # Frontend (HTML, CSS, JS)
│ ├── index.html # Halaman login
│ ├── pages/ # Halaman dashboard, katalog, dll
│ ├── css/ # TailwindCSS
│ ├── public/ # File statis
│ └── tailwind.config.js # Konfigurasi Tailwind
└── README.md


---

## ⚙️ Cara Menjalankan

### 🔧 Backend (Java)
1. Buka folder `BACKEND/` di IDE seperti IntelliJ atau Eclipse.
2. Pastikan sudah setup koneksi database di `config/`.
3. Jalankan aplikasi backend (Spring Boot atau Servlet sesuai strukturmu).

### 💻 Frontend
```bash
cd FRONTEND
npm install      # jika menggunakan Node.js
npm run dev      # atau gunakan Live Server di VS Code
