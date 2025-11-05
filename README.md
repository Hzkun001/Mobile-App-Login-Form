# 📱 Mobile Programming — Form Login + Remember Me
 

Aplikasi Android sederhana berbasis **Jetpack Compose**  
yang menampilkan simulasi login palsu dengan opsi **“Remember Me”** menggunakan **DataStore Preferences**.  
Jika opsi aktif, username akan tersimpan dan ketika aplikasi dibuka kembali,  
pengguna langsung diarahkan ke Dashboard tanpa harus login ulang.

---

## ✅ Fitur Utama

✅ 3 Screen utama  
1) **Splash / Welcome**  
   - Cek data tersimpan (username) dari DataStore  
   - Jika ada → langsung ke Dashboard  
   - Jika tidak → tombol “Masuk”  

2) **Form Login**  
   - Input username & password (statis: `mhs` / `123`)  
   - Checkbox “Ingat saya”  
   - Tombol Login  
   - Validasi & pesan error  
   - Toggle show/hide password  
   - Loading indicator  

3) **Dashboard**  
   - Tampilkan “Selamat datang, <username>”  
   - Tombol “Hapus Data Login / Keluar” → hapus DataStore → kembali Login  

✅ Penyimpanan username memakai DataStore Preferences  
✅ Navigasi menggunakan Navigation Compose (bukan `finish()`)  
✅ Animasi ringan + UI modern  
✅ Arsitektur rapi: pemisahan UI — ViewModel — Repository  

---

## 🧠 Teknologi & Library

| Teknologi | Keterangan |
|----------|------------|
| Jetpack Compose | UI deklaratif |
| Material 3 | Komponen UI modern |
| Navigation Compose | Multi-screen |
| ViewModel | State management |
| DataStore Preferences | Penyimpanan sederhana |
| Flow + Coroutines | Reactive programming |

---

## 🏛️ Struktur Folder

```
id/antasari/uts_mp_230104040118/
│
├─ MainActivity.kt
├─ data/
│   ├─ Datastore.kt
│   └─ AuthRepository.kt
├─ presentation/
│   ├─ AuthViewModel.kt
│   └─ AuthVMFactory.kt
├─ ui/
│   ├─ theme/
│   │   └─ AppTheme.kt
│   ├─ navigation/
│   │   ├─ NavGraph.kt
│   │   └─ Routes.kt
│   └─ screens/
│       ├─ SplashScreen.kt
│       ├─ LoginScreen.kt
│       └─ DashboardScreen.kt
```

---

## 🔐 Alur Login

| Step | Keterangan |
|------|------------|
| 1 | User isi username + password |
| 2 | Sistem cek kredensial statis (`mhs` / `123`) |
| 3 | Jika centang Remember → simpan username |
| 4 | Arahkan ke Dashboard |
| 5 | Dashboard tampilkan username |
| 6 | Logout → hapus DataStore |
| 7 | Kembali ke Login |

---

## 🎨 Tampilan

✅ **Splash Screen**  
- Animasi logo  
- Tombol masuk jika belum login  
- Auto-route jika Remember aktif  

✅ **Login Screen**  
- Username  
- Password (toggle visibility)  
- Checkbox Remember Me  
- Error chip  
- Loading indicator  

✅ **Dashboard Screen**  
- Selamat datang, <username>  
- Avatar inisial  
- Tombol logout → hapus DataStore  

---

## 🔧 Konfigurasi Gradle

Tambahkan di `build.gradle (app)`:

```gradle
buildFeatures {
    compose true
}

composeOptions {
    kotlinCompilerExtensionVersion = "1.5.15"
}
```

Dependency wajib:
```gradle
implementation(platform("androidx.compose:compose-bom:2024.10.01"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.navigation:navigation-compose:2.8.3")
implementation("androidx.datastore:datastore-preferences:1.1.1")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
```

---

## ▶️ Cara Menjalankan

1. Clone repo:
```
git clone https://github.com/Hzkun001/Mobile-App-Login-Form.git
```

2. Buka di Android Studio

3. Sync Gradle

4. Build → Run

5. Login memakai kredensial:
```
Username: mhs
Password: 123
```

Jika centang **Ingat saya**, aplikasi akan langsung masuk Dashboard saat dibuka ulang.

---

## 📝 Catatan

- Data hanya menyimpan username (tanpa password)  
- DataStore menyimpan key-value lokal  
- Navigasi Compose aman (tidak memakai `finish()`)  
- MVVM sederhana → mudah dikembangkan  

---

## Pengembangan Lanjut (Optional)

- Ganti DataStore → Room DB  
- API login real  
- Multi-user  
- Animasi Lottie  
- Dynamic Color (Material You)  

---

---
