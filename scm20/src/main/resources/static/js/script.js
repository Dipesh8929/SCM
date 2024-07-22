console.log("script loaded");

let currentTheme=getTheme();
// work initially
changeTheme();

// todo
function changeTheme(){
    // set to web page

    changePageTheme(currentTheme,"");
// set the listener to change theme button
    const changeThemeButton=document.querySelector("#theme_change_button");
    
    
    changeThemeButton.addEventListener("click",(event)=>
    {
        let oldTheme = currentTheme;
    console.log("clicked");
    if(currentTheme === "dark")
        {
            // theme to light;
            currentTheme="light";
        }
        else{
            // theme to dark
            currentTheme="dark";
        }

        changePageTheme(currentTheme,oldTheme);
   
    });
}
// set theme to local storage
function setTheme(theme)
{
    localStorage.setItem("theme",theme);
}

// get theme from local storage
function getTheme(){
    let theme=localStorage.getItem("theme");
    if(theme)
        {
            return theme;
        }
        else return "light";
}
function changePageTheme(theme,oldTheme){
     // update localstorage
     setTheme(currentTheme);
     // remove the current theme
    if(oldTheme)
        {
            document.querySelector("html").classList.remove(oldTheme);
        }
// set the current theme
     document.querySelector("html").classList.add(theme);

     // change the text of the button
     document.querySelector('#theme_change_button').querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
    }