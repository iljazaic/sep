// Elements
const navToggle = document.getElementById("navToggle");
const mainNav = document.getElementById("mainNav");

// Mobile nav toggle
if (navToggle && mainNav) {
  navToggle.addEventListener("click", () => {
    navToggle.classList.toggle("open");
    mainNav.classList.toggle("open");
  });
}

// Close mobile menu when resizing to tablet/desktop
window.addEventListener("resize", () => {
  if (window.innerWidth >= 800 && navToggle && mainNav) {
    navToggle.classList.remove("open");
    mainNav.classList.remove("open");
  }
});

// Smooth scroll for in-page links
document.querySelectorAll('a[href^="#"]').forEach((link) => {
  link.addEventListener("click", (e) => {
    const targetId = link.getAttribute("href").substring(1);
    const target = document.getElementById(targetId);
    if (target) {
      e.preventDefault();
      window.scrollTo({
        top: target.offsetTop - 72,
        behavior: "smooth",
      });

      // close mobile nav after clicking
      if (navToggle && mainNav) {
        navToggle.classList.remove("open");
        mainNav.classList.remove("open");
        navToggle.style.color = "#59c745";
      }
    }
  });
});
