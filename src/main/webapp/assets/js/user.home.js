// const $ = document.querySelector.bind(document);
// const $$ = document.querySelectorAll.bind(document);

const listData = [{
    id: 1,
    name: "Nike Air Force 1 LV8",
    price: 1100,
    soLuong: 25,
    thuonghieu: "Nike",
    theLoai: "thethao",
    chatlieu: "Dalon",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/46fc5159-8892-472c-acc9-a216d85123ed/air-force-1-lv8-big-kids-shoes-8JtCbS.png"
}, {
    id: 2,
    name: "Air Jordan 1 LV8",
    price: 1000,
    soLuong: 30,
    thuonghieu: "Adidas",
    theLoai: "thethao",
    chatlieu: "Da",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/dadbbaff-8566-427f-9a3c-23b1277cc71d/air-jordan-1-elevate-low-se-womens-shoes-Q630Pk.png"
}, {
    id: 3,
    name: "Nike Air Force 1 Low ASW",
    price: 800,
    soLuong: 70,
    thuonghieu: "Adidas",
    theLoai: "thethao",
    chatlieu: "Da",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/18734018-620a-4d31-bebf-eeeaa7e3cc76/air-force-1-07-lv8-mens-shoes-M866WJ.png"
}, {
    id: 4,
    name: "Nike Air Max Correlate",
    price: 500,
    soLuong: 40,
    thuonghieu: "Adidas",
    theLoai: "thethao",
    chatlieu: "Da",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/fabaa569-6800-419f-be28-cf794a467636/air-max-correlate-womens-shoes-29Vjp0.png"
}, {
    id: 5,
    name: "Nike Air Force 1 Low Retro QS",
    price: 400,
    soLuong: 80,
    thuonghieu: "Adidas",
    theLoai: "thethao",
    chatlieu: "Da",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/3ed444e1-aa65-46bc-b89d-f084b0a62505/air-force-1-low-retro-qs-mens-shoes-XZG2zG.png"
}, {
    id: 6,
    name: "Nike Blazer Mid Pro Club",
    price: 150,
    soLuong: 55,
    thuonghieu: "Nike",
    theLoai: "giaytay",
    chatlieu: "Kanvas",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/9c873d33-58ff-4a69-a28c-9deabb3631eb/blazer-mid-pro-club-mens-shoes-Vgslvc.png"
}, {
    id: 7,
    name: "Air Jordan 1 Elevante Low SE",
    price: 1200,
    soLuong: 35,
    thuonghieu: "Puma",
    theLoai: "thethao",
    chatlieu: "Kanvas",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/c81d673c-6951-4c85-b34e-e1e8a618d54a/air-jordan-1-elevate-low-se-womens-shoes-Q630Pk.png"
}, {
    id: 8,
    name: "Nike Air Force 1 Low ASW",
    price: 750,
    soLuong: 30,
    thuonghieu: "Puma",
    theLoai: "giayluoi",
    chatlieu: "Kanvas",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/92511553-5c5c-4da6-96da-96a51dc701ed/air-force-1-07-mid-womens-shoes-2NP34w.png"
}, {
    id: 9,
    name: "Jordan Nu Netro 1 Low",
    price: 250,
    soLuong: 110,
    thuonghieu: "Nike",
    theLoai: "giaytay",
    chatlieu: "Dalon",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/c7ce7765-18d6-4554-ab4f-cc2b39b9b18f/jordan-nu-retro-1-low-mens-shoes-L6Xk8z.png"
}, {
    id: 10,
    name: "Nike Air Force 1 LV8",
    price: 50,
    soLuong: 60,
    thuonghieu: "Nike",
    theLoai: "giayluoi",
    chatlieu: "Dalon",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/5385fbe8-070b-4148-a56b-186827d9f2a2/air-force-1-lv8-big-kids-shoes-sT7BZs.png"
}, {
    id: 11,
    name: "Nike Air Force 1 LV8",
    price: 1100,
    soLuong: 25,
    thuonghieu: "Nike",
    theLoai: "thethao",
    chatlieu: "Dalon",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/46fc5159-8892-472c-acc9-a216d85123ed/air-force-1-lv8-big-kids-shoes-8JtCbS.png"
}, {
    id: 12,
    name: "Air Jordan 1 LV8",
    price: 1000,
    soLuong: 30,
    thuonghieu: "Adidas",
    theLoai: "thethao",
    chatlieu: "Da",
    img: "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/dadbbaff-8566-427f-9a3c-23b1277cc71d/air-jordan-1-elevate-low-se-womens-shoes-Q630Pk.png"
},

]

const keyLocalStorageListSP = "DACHSACHSP"
// const home = $(".grid")


// luu giu lieu vào localstorage

const setDataStorage = (key, value) => {
    localStorage.setItem(key, value)
}
setDataStorage(keyLocalStorageListSP, JSON.stringify(listData))


// lấy dữ liệu từ Storage
const getDataStorage = (key) => {
    return JSON.parse(localStorage.getItem(key))
}

const datas = getDataStorage(keyLocalStorageListSP)

//  hiển thị sản phẩm
const renderData = () => {
//   const main = $$(".grid");
    const main = document.querySelectorAll('.grid');
    if (main.length > 0) {
        let count = 0; // tạo biến đếm số lượng sản phẩm đã render
        const item = datas.map((data, index) => {
            if (count < 10) { // kiểm tra nếu số lượng sản phẩm đã render chưa đạt 10 thì tiếp tục render
                count++; // tăng biến đếm lên một
                return `
          <div class="grid__column-2-4">
              <div class="home-product-item">
                  <div class="home-product-item__img home-product-add-cart"
                      style="background-image: url(${data.img})"></div>
                  <div class="home-product-item__properties">
                      <h5 class="home-product-item__name">${data.name}</h5>
                      <div class="home-product-item__infor">
                          <div class="home-product-item__price">$ ${data.price}</div>
                          <div class="home-product-item__quantity">Quantity: ${data.soLuong}</div>
                      </div>
                      <div title="Thêm vào giỏ hàng" class="home-product-item__add-cart" )">
                          <i class="fa-solid fa-cart-plus"></i>
                      </div>
                  </div>
              </div>
          </div>`;
            }
        });
        main.forEach((element) => {
            element.innerHTML = item.join("");
        });
    }

};


