const handleClickCart = () => {
    const listCart = document.getElementsByClassName("header__cart-list")[0];
    if (listCart.classList.contains("open-cart")) {
        listCart.classList.remove("open-cart")
    } else {
        listCart.classList.add("open-cart")
    }
}

window.addEventListener("click", (e) => {
    const listCart = document.getElementsByClassName("header__cart-list")[0];
    const iconCart = document.getElementsByClassName("header__cart-icon")[0];
    if (!listCart.contains(e.target) && !iconCart.contains(e.target)) {
        listCart.classList.add("open-cart")
    }
})


// contact messenger
function showInfoBox(event) {
    const infoBox = document.getElementById("infoBox")
    infoBox.style.display = "block"
    infoBox.style.left = event.clientX + "px"
    infoBox.style.top = event.clientY + "px"
}

function hideInfoBox() {
    const infoBox = document.getElementById("infoBox")
    infoBox.style.display = "none"
}

// Drop down account
const dropdownAccount = document.getElementsByClassName('dropdown-account-link')[0];
const btnAccountItem = document.getElementsByClassName('account-item')[0];

btnAccountItem.addEventListener('click', () => {
    dropdownAccount.classList.contains('open-dropdown') ? dropdownAccount.classList.remove('open-dropdown') : dropdownAccount.classList.add('open-dropdown');
});

window.onclick = (event) => {
    if (!dropdownAccount.contains(event.target) && !btnAccountItem.contains(event.target)) {
        dropdownAccount.classList.remove('open-dropdown');
    }
};