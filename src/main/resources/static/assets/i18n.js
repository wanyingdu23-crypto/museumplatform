(function () {
  var STORAGE_KEY = "museum_language";

  var translations = {
    en: {
      common_back: "Back",
      common_close: "Close",
      common_send: "Send",
      common_leave: "Leave",
      common_cancel: "Cancel",
      common_continue: "Continue",
      common_exit: "Exit",
      common_expand: "..Expand",
      common_collapse: "Collapse",
      common_saved: "Saved. View it in My Profile - Favorites.",
      common_removed: "Removed from favorites.",
      common_no_records: "No records yet.",
      common_ask_question: "Type your question...",
      common_unexpected: "Unexpected server response.",

      home_title: "Museum Stories",
      home_feedback: "Feedback",
      home_turtle: "Turtle Soup",
      home_puzzle: "Puzzle",
      home_offline: "Offline View",
      home_feedback_intro: "Please enter your suggestion and we will improve it.",
      home_feedback_placeholder: "Share your feedback...",
      home_send: "Send",
      home_thanks: "Thank you for your suggestion.",
      home_lang: "中/ENG",
      home_title_doc: "Home - My Museum Stories",

      welcome_welcome: "WELCOME",
      welcome_brand: "My Museum Stories",
      welcome_name: "My Museum Stories",
      welcome_subtitle: "Museum stories, quietly curated.",
      welcome_login: "Login",
      welcome_register: "Register",
      welcome_title_doc: "My Museum Stories",

      login_title: "Login",
      login_desc: "Enter your account to continue.",
      login_account: "Username or Email",
      login_account_ph: "Enter your username or email",
      login_password: "Password",
      login_password_ph: "Enter your password",
      login_button: "Login",
      login_title_doc: "Login - My Museum Stories",

      register_title: "Register",
      register_desc: "Create your account.",
      register_username: "Username",
      register_username_ph: "Choose a username",
      register_email: "Email",
      register_email_ph: "Enter your email address",
      register_password: "Password",
      register_password_ph: "Create a password",
      register_confirm: "Confirm Password",
      register_confirm_ph: "Re-enter your password",
      register_button: "Register",
      register_title_doc: "Register - My Museum Stories",

      show_password: "Show password",
      hide_password: "Hide password",

      profile_section: "My Features",
      profile_turtle: "Turtle Soup History",
      profile_puzzle: "Puzzle History",
      profile_favorites: "My Favorites",
      profile_signout: "Sign Out",
      profile_delete: "Delete Account",
      profile_password: "Password:",
      profile_title_doc: "Profile - My Museum Stories",

      records_favorites_title: "Favorites",
      records_favorites_subtitle: "All collected artifacts are shown here.",
      records_puzzle_title: "Puzzle History",
      records_puzzle_subtitle: "All puzzle-related artifact records are shown here.",
      records_turtle_title: "Turtle Soup History",
      records_turtle_subtitle: "All Turtle Soup-related artifact records are shown here.",
      records_title_doc: "Records - My Museum Stories",

      turtle_title_doc: "Turtle Soup - My Museum Stories",
      turtle_view_answer: "View Answer",
      turtle_submit_answer: "Submit My Answer",
      turtle_input_placeholder: "Type your answer...",
      turtle_leave_title: "Leave This Page?",
      turtle_leave_text: "This page has not been saved.",
      turtle_continue_game: "Continue Game",
      turtle_correct: "Correct answer.",
      turtle_wrong: "no",

      turtle_artifact_title_doc: "Artifact - My Museum Stories",
      artifact_intro_fallback: "Artifact Introduction",

      puzzle_title_doc: "Puzzle - My Museum Stories",
      puzzle_view_hint: "View Hint",
      puzzle_finish: "Finish",
      puzzle_leave_title: "Leave This Page?",
      puzzle_leave_text: "This page has not been saved.",
      puzzle_incomplete_title: "Puzzle Not Finished",
      puzzle_incomplete_text: "The puzzle is not complete yet.",
      puzzle_continue_game: "Continue Game",

      puzzle_artifact_title_doc: "Puzzle Artifact - My Museum Stories",

      offline_title_doc: "Offline View - My Museum Stories",
      offline_scan_here: "Scan Here",
      offline_allow_camera: "Please allow camera access",
      offline_simulate_scan: "Simulate Scan",
      offline_ask_ai: "ASK AI",
      offline_sheet_title: "DeepSeek",
      offline_sheet_placeholder: "Ask about the scanned artifact and DeepSeek will reply here."
    },
    zh: {
      common_back: "返回",
      common_close: "关闭",
      common_send: "发送",
      common_leave: "离开",
      common_cancel: "取消",
      common_continue: "继续",
      common_exit: "退出",
      common_expand: "..展开",
      common_collapse: "收起",
      common_saved: "已收藏，可在我的主页 - 收藏中查看。",
      common_removed: "已取消收藏。",
      common_no_records: "暂无记录。",
      common_ask_question: "输入你的问题...",
      common_unexpected: "服务器返回异常。",

      home_title: "博物馆故事",
      home_feedback: "反馈",
      home_turtle: "海龟汤",
      home_puzzle: "拼图",
      home_offline: "线下查看",
      home_feedback_intro: "请输入您的建议，我们会进行改善。",
      home_feedback_placeholder: "请输入您的建议...",
      home_send: "发送",
      home_thanks: "感谢您的建议。",
      home_lang: "中/ENG",
      home_title_doc: "主页 - 我的博物馆故事",

      welcome_welcome: "欢迎",
      welcome_brand: "我的博物馆故事",
      welcome_name: "我的博物馆故事",
      welcome_subtitle: "静静策展的博物馆故事。",
      welcome_login: "登录",
      welcome_register: "注册",
      welcome_title_doc: "我的博物馆故事",

      login_title: "登录",
      login_desc: "输入你的账号以继续。",
      login_account: "用户名或邮箱",
      login_account_ph: "请输入用户名或邮箱",
      login_password: "密码",
      login_password_ph: "请输入密码",
      login_button: "登录",
      login_title_doc: "登录 - 我的博物馆故事",

      register_title: "注册",
      register_desc: "创建你的账号。",
      register_username: "用户名",
      register_username_ph: "请输入用户名",
      register_email: "邮箱",
      register_email_ph: "请输入邮箱地址",
      register_password: "密码",
      register_password_ph: "请输入密码",
      register_confirm: "确认密码",
      register_confirm_ph: "请再次输入密码",
      register_button: "注册",
      register_title_doc: "注册 - 我的博物馆故事",

      show_password: "显示密码",
      hide_password: "隐藏密码",

      profile_section: "我的功能",
      profile_turtle: "海龟汤记录",
      profile_puzzle: "拼图记录",
      profile_favorites: "我的收藏",
      profile_signout: "退出登录",
      profile_delete: "注销账号",
      profile_password: "密码：",
      profile_title_doc: "我的 - 我的博物馆故事",

      records_favorites_title: "收藏内容",
      records_favorites_subtitle: "这里展示全部收藏的文物。",
      records_puzzle_title: "拼图记录",
      records_puzzle_subtitle: "这里展示全部拼图相关文物记录。",
      records_turtle_title: "海龟汤记录",
      records_turtle_subtitle: "这里展示全部海龟汤相关文物记录。",
      records_title_doc: "记录 - 我的博物馆故事",

      turtle_title_doc: "海龟汤 - 我的博物馆故事",
      turtle_view_answer: "查看答案",
      turtle_submit_answer: "提交我的答案",
      turtle_input_placeholder: "输入你的答案...",
      turtle_leave_title: "离开此页面？",
      turtle_leave_text: "此页面尚未保存。",
      turtle_continue_game: "继续游戏",
      turtle_correct: "回答正确。",
      turtle_wrong: "no",

      turtle_artifact_title_doc: "文物介绍 - 我的博物馆故事",
      artifact_intro_fallback: "文物介绍",

      puzzle_title_doc: "拼图 - 我的博物馆故事",
      puzzle_view_hint: "查看提示",
      puzzle_finish: "完成",
      puzzle_leave_title: "离开此页面？",
      puzzle_leave_text: "此页面尚未保存。",
      puzzle_incomplete_title: "拼图尚未完成",
      puzzle_incomplete_text: "当前拼图还没有完成。",
      puzzle_continue_game: "继续游戏",

      puzzle_artifact_title_doc: "拼图文物介绍 - 我的博物馆故事",

      offline_title_doc: "线下查看 - 我的博物馆故事",
      offline_scan_here: "扫描处",
      offline_allow_camera: "请允许摄像头权限",
      offline_simulate_scan: "模拟扫描",
      offline_ask_ai: "ASK AI",
      offline_sheet_title: "豆包",
      offline_sheet_placeholder: "可针对扫描到的文物继续提问，DeepSeek 会在这里回复。"
    }
  };

  function normalizeLanguage(lang) {
    return lang === "zh" ? "zh" : "en";
  }

  function getLanguage() {
    return normalizeLanguage(localStorage.getItem(STORAGE_KEY) || "en");
  }

  function setLanguage(lang) {
    localStorage.setItem(STORAGE_KEY, normalizeLanguage(lang));
  }

  function toggleLanguage() {
    var next = getLanguage() === "en" ? "zh" : "en";
    setLanguage(next);
    return next;
  }

  function t(key) {
    var lang = getLanguage();
    return (translations[lang] && translations[lang][key]) || (translations.en && translations.en[key]) || key;
  }

  function setText(selector, key) {
    var node = document.querySelector(selector);
    if (node) {
      node.textContent = t(key);
    }
  }

  function setPlaceholder(selector, key) {
    var node = document.querySelector(selector);
    if (node) {
      node.placeholder = t(key);
    }
  }

  function applyPage(page) {
    if (page === "home") {
      setText(".page-title", "home_title");
      setText("#feedbackButton span", "home_feedback");
      setText(".feature-card[data-target='turtle-sea.html'] .feature-title", "home_turtle");
      setText(".feature-card[data-target='puzzle.html'] .feature-title", "home_puzzle");
      setText(".feature-card[data-target='offline-view.html'] .feature-title", "home_offline");
      setText("#feedbackArea", "home_feedback_intro");
      setPlaceholder("#feedbackInput", "home_feedback_placeholder");
      setText("#feedbackSend", "home_send");
      setText("#languageToggle", "home_lang");
      document.title = t("home_title_doc");
    }

    if (page === "welcome") {
      setText(".welcome", "welcome_welcome");
      setText(".eyebrow", "welcome_brand");
      setText(".app-name", "welcome_name");
      setText(".subtitle", "welcome_subtitle");
      setText("#loginButton", "welcome_login");
      setText("#registerButton", "welcome_register");
      document.title = t("welcome_title_doc");
    }

    if (page === "login") {
      setText(".title", "login_title");
      setText(".description", "login_desc");
      setText("label[for='account']", "login_account");
      setPlaceholder("#account", "login_account_ph");
      setText("label[for='password']", "login_password");
      setPlaceholder("#password", "login_password_ph");
      setText(".button", "login_button");
      setText(".back-link", "common_back");
      document.title = t("login_title_doc");
    }

    if (page === "register") {
      setText(".title", "register_title");
      setText(".description", "register_desc");
      setText("label[for='username']", "register_username");
      setPlaceholder("#username", "register_username_ph");
      setText("label[for='email']", "register_email");
      setPlaceholder("#email", "register_email_ph");
      setText("label[for='password']", "register_password");
      setPlaceholder("#password", "register_password_ph");
      setText("label[for='confirmPassword']", "register_confirm");
      setPlaceholder("#confirmPassword", "register_confirm_ph");
      setText(".button", "register_button");
      setText(".back-link", "common_back");
      document.title = t("register_title_doc");
    }

    if (page === "profile") {
      setText(".section-title", "profile_section");
      var titles = document.querySelectorAll(".card-title, .wide-title");
      if (titles[0]) { titles[0].textContent = t("profile_turtle"); }
      if (titles[1]) { titles[1].textContent = t("profile_puzzle"); }
      if (titles[2]) { titles[2].textContent = t("profile_favorites"); }
      setText("#passwordLabel", "profile_password");
      setText("#logoutButton", "profile_signout");
      setText("#exitButton", "profile_delete");
      document.title = t("profile_title_doc");
    }
  }

  window.MuseumI18n = {
    getLanguage: getLanguage,
    setLanguage: setLanguage,
    toggleLanguage: toggleLanguage,
    t: t,
    applyPage: applyPage
  };
})();
