# 📟 ziPosMobile — Android Point of Sale App

**POSify** is a modern mobile-first Point of Sale (POS) Android app built in Kotlin using Firebase and Jetpack Compose. It is designed to empower small-to-medium businesses to manage inventory, sales, and staff all in one streamlined mobile experience.

---

## 📱 Features

- 💳 **Sales Management**

  - Add to cart, apply discounts, multi-currency checkout
  - Generate & share PDF invoices
  - Refunds and order history

- 🛒 **Inventory Management**

  - Add/edit/delete products
  - Low stock alerts
  - Barcode scanning (coming soon)

- 👥 **User Roles & Permissions**

  - Admin, Cashier, and Inventory Clerk roles
  - Role-based access control throughout the app

- 💬 **In-App Messaging**

  - Staff can message each other in real-time using Firestore chat rooms

- 📈 **Smart Dashboard**

  - Daily sales summary, best-sellers, low stock warnings
  - AI-based insights like most profitable items

- 🌍 **Multi-Store & Multi-Currency Support**

  - Staff are scoped to stores
  - Currency formatting based on locale

- 🛠️ **Offline Mode** (Local Room DB sync)

  - Product inventory and cart stored locally
  - Sync with Firestore when online

---

## 🛠️ Tech Stack

| Layer            | Technology                                 |
| ---------------- | ------------------------------------------ |
| Language         | Kotlin                                     |
| UI Framework     | Jetpack Compose                            |
| Architecture     | MVVM + Repository                          |
| Database (local) | Room                                       |
| Backend          | Firebase Firestore, Firebase Auth          |
| Realtime Chat    | Firestore subcollections & listeners       |
| Invoicing        | Android PDF APIs                           |
| Charts           | Compose Charts / MPAndroidChart (optional) |
| Build Tool       | Gradle                                     |

---

## 🧑‍💻 Developer Setup

### 🔧 Prerequisites

- Android Studio Electric Eel or newer
- Firebase project with:
  - Firestore enabled
  - Firebase Authentication (email & password)
  - Firebase Storage (for invoice sharing if needed)
- Kotlin 1.9+
- Java 17+
- Internet connection (for Firebase features)

---

### 🚀 Clone and Run

```bash
git clone https://github.com/yourusername/posify-android.git
cd posify-android
```

1. Open the project in Android Studio.
2. Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/).
3. Add your Android app’s SHA-1 and package name in Firebase.
4. Download `google-services.json` and place it in `app/` directory.
5. Sync Gradle and run the app on emulator or physical device.

---

### 🔐 Firebase Rules (Minimal Example)

```js
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /stores/{storeId}/staff/{userId} {
      allow read, write: if request.auth.uid == userId;
    }

    match /chats/{chatId} {
      allow read, write: if request.auth != null;
    }

    match /sales/{saleId} {
      allow read, write: if request.auth != null;
    }
  }
}
```

---

### 🔄 Project Structure

```
- auth/           // Login, Register, Role handling
- sales/          // Cart, Checkout, History
- inventory/      // Product add/edit/list
- dashboard/      // Charts, KPIs
- chat/           // In-app messaging
- common/         // Reusable components, themes
```

---

## 📌 TODOs & Roadmap

## 📌 TODOs & Roadmap

### ✅ Completed

- [x] Firebase Authentication with role support  
- [x] Inventory and product management with Room & Firestore sync  
- [x] Offline cart and sales flow  
- [x] Real-time in-app messaging for staff  
- [x] Multi-currency and discount-aware checkout  
- [x] Dashboard with KPIs and sales trends  
- [x] PDF invoice generation and sharing  
- [x] Role-based access restrictions  

### 🔜 In Progress

- [ ] Barcode scanning with ML Kit / ZXing  
- [ ] Export sales and inventory to CSV  
- [ ] AI-powered sales insights using BigQuery or Vertex AI  
- [ ] Basic settings screen for store configuration  
- [ ] Marketing dashboard with customer tracking  

### 💡 Planned

- [ ] Cloud backup and restore of inventory  
- [ ] Tablet & landscape layout support  
- [ ] Multi-language (i18n) support  
- [ ] Staff time-tracking module  
- [ ] Loyalty points and coupons engine  
- [ ] Integration with external printers (USB/Bluetooth)  
- [ ] Stripe/PayPal payment support (optional module)  


---

## 👨‍💼 License

MIT License. Use it, extend it, and build something amazing!

---

**Built with ❤️ in Kotlin and Firebase.**

