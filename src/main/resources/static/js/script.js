console.log("Script loaded...");

let currentTheme = getTheme();

// iniitalize theme
document.addEventListener("DOMContentLoaded", () => {
    changeTheme();
});

// TODO:
function changeTheme() {
    // set to web page
    document.querySelector("html").classList.add(currentTheme);
    
    // set the listener
    const changeThemeButton = document.querySelector("#theme_change_button");
    // update the button text
    changeThemeButton.querySelector("span").textContent = currentTheme === "light" ? "Dark" : "Light";
    changeThemeButton.addEventListener("click", (event) => {
        // storing the old theme in a var to avoid delay in theme change
        const oldTheme = currentTheme;
        if (currentTheme === "light") {
            currentTheme = "dark";
        } else {
            currentTheme = "light";
        }

        // update the new theme in localstorage
        setTheme(currentTheme);

        // remove the old theme
        document.querySelector("html").classList.remove(oldTheme);

        // set the new theme
        document.querySelector("html").classList.add(currentTheme);

        // update the button text
        changeThemeButton.querySelector("span").textContent = currentTheme === "light" ? "Dark" : "Light";
    });    
}

// set theme to localstorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// get theme from localstorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    if (theme) return theme;
    else return "light";
}