<div align="center">

# 📝 Online Examination System

**A console-based Online Exam simulator built with Core Java — no frameworks, no libraries.**

![Java](https://img.shields.io/badge/Java-8%2B-orange?style=flat-square&logo=java)
![Core Java](https://img.shields.io/badge/Core%20Java-Only-blue?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen?style=flat-square)

</div>

---

## 📌 About the Project

This project simulates a real-world **Online Examination System** using only Core Java. It covers all five required functionalities — Login, Update Profile & Password, MCQ selection, Timer with auto-submit, and Logout — in a clean console interface.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🔐 Login | Authenticate with username & password (3 attempts max) |
| 👤 Update Profile | Change full name and email |
| 🔑 Change Password | Secure password update with confirmation |
| 📋 MCQ Exam | 10 Java-based multiple choice questions |
| ⏱️ Timer & Auto-Submit | 2-minute countdown; auto-submits on timeout |
| 🔁 Navigate Questions | Move Next / Previous freely during exam |
| 📊 Result & Review | Score, grade, and per-question answer review |
| 🚪 Logout | Closes session and returns to login |

---

## 📁 Project Structure

```
OnlineExam/
├── src/
│   └── exam/
│       ├── Main.java            →  Entry point
│       ├── ExamApp.java         →  All menus, UI, navigation logic
│       ├── ExamSession.java     →  Timer, answers, auto-submit, scoring
│       ├── Question.java        →  MCQ question model
│       ├── QuestionBank.java    →  10 pre-loaded Java MCQs
│       ├── User.java            →  User profile, auth, password
│       └── UserDatabase.java    →  In-memory user store (HashMap)
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 8 or higher
- Any terminal / command prompt

### Clone the Repository
```bash
git clone https://github.com/your-username/OnlineExam.git
cd OnlineExam
```

### Compile
```bash
javac -d out src/exam/*.java
```

### Run
```bash
java -cp out exam.Main
```

---

## 💻 IDE Setup

<details>
<summary><strong>IntelliJ IDEA</strong></summary>

1. `File` → `Open` → Select the `OnlineExam` folder
2. Right-click `src` → `Mark Directory as` → `Sources Root`
3. Open `Main.java` → Click ▶️ Run

</details>

<details>
<summary><strong>Eclipse</strong></summary>

1. `File` → `New` → `Java Project` → Name it `OnlineExam`
2. Copy `src/exam/` files into the project `src`
3. Right-click `Main.java` → `Run As` → `Java Application`

</details>

<details>
<summary><strong>VS Code</strong></summary>

1. Install **Extension Pack for Java**
2. Open the `OnlineExam` folder
3. Open `Main.java` → Click **Run** above the `main` method

</details>

---

## 🧾 Demo Accounts

| Username   | Password  | Full Name    |
|:----------:|:---------:|:------------:|
| `student1` | `pass123` | Ravi Sharma  |
| `student2` | `pass456` | Priya Mehta  |
| `student3` | `pass789` | Amit Kumar   |

---

## 🖥️ Sample Output

```
  ╔═══════════════════════════════════════════╗
  ║        ONLINE EXAMINATION SYSTEM          ║
  ║           Core Java Project               ║
  ╚═══════════════════════════════════════════╝

  Username: student1
  Password: pass123
  ✓ Login successful! Welcome, Ravi Sharma.

  ╔══════════════════════════════════╗
  ║         STUDENT DASHBOARD        ║
  ╠══════════════════════════════════╣
  ║  1. Start Exam                   ║
  ║  2. Update Profile               ║
  ║  3. Change Password              ║
  ║  4. Logout                       ║
  ╚══════════════════════════════════╝

  ╔════════════════════════════════════════════════════╗
  ║  ONLINE EXAM  │  Q 01 / 10  │  Time: 01:47 remaining  ║
  ║  Student: Ravi Sharma         Attempted: 00/10   ║
  ╚════════════════════════════════════════════════════╝

  Q1: Which keyword is used to define a class in Java?

      1. define
      2. class
      3. struct
      4. object

  Enter 1-4 to select answer  │  N=Next  P=Prev  S=Submit

  ╔══════════════════════════════════════════╗
  ║              EXAM RESULT                 ║
  ╠══════════════════════════════════════════╣
  ║  Student  : Ravi Sharma                  ║
  ║  Score    : 8 / 10  (80.0%)              ║
  ║  Grade    : A                            ║
  ╚══════════════════════════════════════════╝
```

---

## 🧠 Core Java Concepts Used

```
✔ Classes & Objects       →  User, Question, ExamSession, QuestionBank
✔ Encapsulation           →  Private fields with public methods
✔ HashMap                 →  UserDatabase stores users by username
✔ Arrays                  →  Questions array, answers array
✔ Scanner                 →  Console input
✔ Switch-Case             →  Menu and exam navigation
✔ Loops                   →  Login retry, exam loop, result review
✔ System.currentTimeMillis→  Real-time countdown timer
✔ Thread.sleep            →  Pause for UX feedback
✔ String.format           →  Formatted output tables
✔ Exception Handling      →  Input safety
```

---

## 🗺️ Application Flow

```
START
  │
  ▼
Login (username + password)
  │
  ├── Wrong × 3  ──▶  Locked
  │
  └── Correct ──▶  Dashboard
                        │
          ┌─────────────┼──────────────┐
          ▼             ▼              ▼
    Start Exam    Update Profile  Change Password
          │
          ▼
    Exam Begins (Timer starts)
          │
          ├── Navigate Q1 → Q10 (Next/Prev)
          ├── Select answer (1-4)
          ├── Submit manually OR auto-submit on timeout
          │
          ▼
    Show Result + Answer Review
          │
          ▼
    Back to Dashboard
          │
          ▼
        Logout
```

---

## 🤝 Contributing

1. Fork the project
2. Create your branch: `git checkout -b feature/your-feature`
3. Commit: `git commit -m 'Add your feature'`
4. Push: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

Licensed under the **MIT License**.

---

<div align="center">
  <sub>Built as a learning project · Core Java · No external dependencies</sub>
</div>
<img width="1757" height="1002" alt="Screenshot 2026-05-12 155948" src="https://github.com/user-attachments/assets/dce8b296-7e77-484c-9083-45e6cf75914d" />
<img width="1611" height="1022" alt="Screenshot 2026-05-12 160009" src="https://github.com/user-attachments/assets/488a9ed7-16fb-4bca-bd46-1daf575b3fd4" />
<img width="1612" height="1005" alt="Screenshot 2026-05-12 160018" src="https://github.com/user-attachments/assets/95dbd8c0-9ae1-422b-b21b-68587b5d2672" />
<img width="1606" height="983" alt="Screenshot 2026-05-12 160031" src="https://github.com/user-attachments/assets/a50d7cfe-d1f6-42a8-abad-dcfe4047201b" />
<img width="1710" height="1021" alt="Screenshot 2026-05-12 160043" src="https://github.com/user-attachments/assets/2bce6741-3241-48f3-a7cd-a3ccc8c2ebc4" />
<img width="1643" height="1006" alt="Screenshot 2026-05-12 160055" src="https://github.com/user-attachments/assets/5cdba554-8703-4e7b-be06-57e0e9152e16" />
<img width="1710" height="987" alt="Screenshot 2026-05-12 160104" src="https://github.com/user-attachments/assets/57dd135d-b7b2-45cd-ba04-6b4a07f04ceb" />




