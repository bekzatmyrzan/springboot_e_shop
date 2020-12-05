package com.example.AllEShop.Controllers;

import com.example.AllEShop.entities.*;
import com.example.AllEShop.services.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RoleService roleService;

    @Value("${file.avatar.viewPath}")
    private String viewPath;

    @Value("${file.item.viewPath}")
    private String viewPathForItem;

    @Value("${file.avatar.uploadPath}")
    private String uploadPath;

    @Value("${file.item.uploadPath}")
    private String uploadPathForItem;


    @Value("${file.avatar.defaultPicture}")
    private String defaultPicture;


    @GetMapping(value = "/")
    public String index(Model model) {
        ArrayList<Item> items = itemService.getAllItemsTopPage();
        model.addAttribute("items", items);
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        List<Language> languages = languageService.getAllLanguages();
        model.addAttribute("currentUser", getUserData());
        return "index";
    }

    @GetMapping(value = "/admin_items")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_items(Model model) {
        ArrayList<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        List<Language> languages = languageService.getAllLanguages();
        model.addAttribute("currentUser", getUserData());
        return "admin_items";
    }

    @GetMapping(value = "/admin_categories")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_categories(Model model) {
        ArrayList<Category> categories = (ArrayList<Category>) categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("currentUser", getUserData());
        return "admin_categories";
    }

    @GetMapping(value = "/admin_countries")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_countries(Model model) {
        ArrayList<Country> countries = (ArrayList<Country>) countryService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("currentUser", getUserData());
        return "admin_countries";
    }

    @GetMapping(value = "/admin_brands")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_brands(Model model) {
        ArrayList<Brand> brands = (ArrayList<Brand>) brandService.getAllBrands();
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("brands", brands);
        model.addAttribute("currentUser", getUserData());
        return "admin_brands";
    }

    @GetMapping(value = "/admin_pictures")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_pictures(Model model) {
        ArrayList<Picture> pictures = (ArrayList<Picture>) pictureService.getAllPictures();
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        model.addAttribute("pictures", pictures);
        model.addAttribute("currentUser", getUserData());
        return "admin_pictures";
    }

    @GetMapping(value = "/admin_users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_users(Model model) {
        ArrayList<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", getUserData());
        return "admin_users";
    }

    @GetMapping(value = "/admin_roles")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_roles(Model model) {
        ArrayList<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("currentUser", getUserData());
        return "admin_roles";
    }

    @GetMapping(value = "/admin_role_details/{idshka}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String admin_role_details(Model model, @PathVariable(name = "idshka") Long id) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        model.addAttribute("currentUser", getUserData());
        return "admin_role_details";
    }

    @GetMapping(value = "/admin_item_details/{idshka}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_item_details(Model model, @PathVariable(name = "idshka") Long id) {
        Item item = itemService.getItem(id);
        List<Brand> brands = brandService.getAllBrands();
        List<Category> categories = categoryService.getAllCategories();
        List<Picture> pictures = pictureService.getAllPicturesByItem(item);
        model.addAttribute("pictures", pictures);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("item", item);
        model.addAttribute("currentUser", getUserData());
        return "admin_item_details";
    }

    @GetMapping(value = "/admin_category_details/{idshka}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_category_details(Model model, @PathVariable(name = "idshka") Long id) {
        Category category = categoryService.getCategory(id);
        model.addAttribute("category", category);
        model.addAttribute("currentUser", getUserData());
        return "admin_category_details";
    }

    @GetMapping(value = "/admin_country_details/{idshka}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_country_details(Model model, @PathVariable(name = "idshka") Long id) {
        Country country = countryService.getCountry(id);
        model.addAttribute("country", country);
        model.addAttribute("currentUser", getUserData());
        return "admin_country_details";
    }

    @GetMapping(value = "/admin_brand_details/{idshka}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_brand_details(Model model, @PathVariable(name = "idshka") Long id) {
        Brand brand = brandService.getBrand(id);
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("brand", brand);
        model.addAttribute("countries", countries);
        model.addAttribute("currentUser", getUserData());
        return "admin_brand_details";
    }

    @GetMapping(value = "/admin_user_details/{idshka}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String admin_user_details(Model model, @PathVariable(name = "idshka") Long id) {
        User user = userService.getUserById(id);
        ArrayList<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        model.addAttribute("currentUser", getUserData());
        return "admin_user_details";
    }

    @GetMapping(value = "/allItems")
    public String allItems(Model model) {
        ArrayList<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        List<Country> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("currentUser", getUserData());
        return "index";
    }

    @GetMapping(value = "/details/{idshka}")
    public String details(Model model, @PathVariable(name = "idshka") Long id) {
        Item item = itemService.getItem(id);
        List<Brand> brands = brandService.getAllBrands();
        List<Category> categories = item.getCategories();
        List<Picture> pictures = pictureService.getAllPicturesByItem(item);
        model.addAttribute("pictures", pictures);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("item", item);
        model.addAttribute("currentUser", getUserData());
        return "details";
    }

    @GetMapping(value = "/searchItemsByBrand/{brand_name}")
    public String searchItemsByBrand(Model model,
                                     @PathVariable(name = "brand_name") String brand_name) {
        System.out.println(brand_name + "rrr");
        ArrayList<Item> items = itemService.searchItemsByBrand(brand_name);
        model.addAttribute("items", items);
        model.addAttribute("search_brand_name", brand_name);

        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        model.addAttribute("currentUser", getUserData());
        return "search";
    }

    @GetMapping(value = "/searchItemsByCategory/{category_name}")
    public String searchItemsByCategory(Model model,
                                        @PathVariable(name = "category_name") String category_name) {
        System.out.println(category_name + "rrr");
        Category category = categoryService.getCategoryByName(category_name);
        if (category != null) {
            ArrayList<Item> items = itemService.searchItemsByCategory(category);
            model.addAttribute("items", items);
            model.addAttribute("category_name", category_name);
        }
        model.addAttribute("currentUser", getUserData());
        return "search";
    }

    @GetMapping(value = "/searchItems")
    public String searchItems(Model model,
                              @RequestParam(name = "search_brand_name", defaultValue = "", required = false) String search_brand_name,
                              @RequestParam(name = "search_name", defaultValue = "", required = false) String search_name,
                              @RequestParam(name = "search_price_from", defaultValue = "0.0", required = false) String search_price_from,
                              @RequestParam(name = "search_price_to", defaultValue = "1000000000.0", required = false) String search_price_to,
                              @RequestParam(name = "order_by", defaultValue = "err", required = false) String order_by) {
        System.out.println(search_price_from + "fff");
        System.out.println(search_price_to + "ttt");
        ArrayList<Item> items = itemService.searchItems(search_brand_name, search_name, search_price_from, search_price_to, order_by);
        model.addAttribute("items", items);
        model.addAttribute("search_brand_name", search_brand_name);
        model.addAttribute("search_name", search_name);
        model.addAttribute("search_price_from", search_price_from);
        model.addAttribute("search_price_to", search_price_to);
        model.addAttribute("currentUser", getUserData());

        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        return "search";
    }

    @PostMapping(value = "/addItem")
    public String addItem(@RequestParam(name = "item_name", defaultValue = "Default name") String name,
                          @RequestParam(name = "item_description", defaultValue = "Default description") String description,
                          @RequestParam(name = "item_price", defaultValue = "0") Double price,
                          @RequestParam(name = "item_stars", defaultValue = "0") int stars,
                          @RequestParam(name = "item_smallPicURL", defaultValue = "") String smallPicURL,
                          @RequestParam(name = "item_largePicURL", defaultValue = "") String largePicURL,
                          @RequestParam(name = "item_inTopPage", defaultValue = "0") Boolean inTopPage,
                          @RequestParam(name = "item_brand_id", defaultValue = "0") Long brand_id
    ) {
        Date currentDate = new Date(System.currentTimeMillis());
        Brand brand = brandService.getBrand(brand_id);
        if (brand != null) {
            Item item = new Item(null, name, description, price, stars, smallPicURL, largePicURL, currentDate, inTopPage, brand, null);
            itemService.addItem(item);
        }
        return "redirect:/admin_items";
    }

    @PostMapping(value = "/addPicture")
    public String addPicture(
            @RequestParam(name = "picture_url") MultipartFile file,
            @RequestParam(name = "picture_item_id", defaultValue = "0") Long picture_item_id
    ) {
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            try {
                Item item = itemService.getItem(picture_item_id);
                if (item!=null) {
//                    User currentUser = getUserData();
//                    assert currentUser != null;
                    int length = pictureService.getAllPictures().size();
                    String picName = DigestUtils.sha1Hex("itemPhoto_" + item.getId() + length+ "_!Picture");
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(uploadPathForItem + picName + ".jpg");
                    Files.write(path, bytes);
                    Picture picture = new Picture();
                    picture.setUrl(picName);
                    Date currentDate = new Date(System.currentTimeMillis());
                    picture.setAdded_date(currentDate);
                    picture.setItem(item);
                    //currentUser.setPicture_url(picName);
                    pictureService.savePicture(picture);
                    //userService.saveUser(currentUser);
                    return "redirect:/admin_pictures?success";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/admin_pictures?warning";
    }

    @PostMapping(value = "/addBrand")
    public String addBrand(
            @RequestParam(name = "brand_name", defaultValue = "Default name") String name,
            @RequestParam(name = "brand_picture", defaultValue = "") String brand_picture,
            @RequestParam(name = "brand_country_id", defaultValue = "0") Long country_id
    ) {
        Country country = countryService.getCountry(country_id);
        if (country != null) {
            Brand brand = new Brand(null, name, brand_picture, country);
            brandService.addBrand(brand);
        }
        return "redirect:/admin_brands";
    }

    @PostMapping(value = "/addCategory")
    public String addCategory(
            @RequestParam(name = "category_name", defaultValue = "Default name") String category_name
    ) {
        categoryService.addCategory(new Category(null, category_name));
        return "redirect:/admin_categories";
    }

    @PostMapping(value = "/addUser")
    public String addUser(Model model,
                          @RequestParam(name = "user_email") String email,
                          @RequestParam(name = "user_password") String password,
                          @RequestParam(name = "user_repassword") String repassword,
                          @RequestParam(name = "user_full_name") String full_name
    ) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            if (password.equals(repassword)) {
                ArrayList<Role> roles = new ArrayList<>();
                roles.add(roleService.getSimpleRole());
                userService.saveUser(new User(null, email, passwordEncoder.encode(password), full_name,null, roles));
                model.addAttribute("error", "noerr");
                return "redirect:/login";
            } else {
                model.addAttribute("error", "Incorrect re-password");
                return "registration";

            }
        } else {
            model.addAttribute("error", "User exist");
            return "registration";
        }

    }

    @PostMapping(value = "/setCategory")
    public String setCategory(Model model,
                              @RequestParam(name = "item_id", defaultValue = "0") Long item_id,
                              @RequestParam(name = "category_id", defaultValue = "0") Long category_id
    ) {
        Category category = categoryService.getCategory(category_id);
        Item item = itemService.getItem(item_id);
        if (category != null && item != null) {
            List<Category> categories = item.getCategories();
            if (categories == null) {
                categories = new ArrayList<>();
            }
            if (categories.contains(category)) {
                categories.remove(category);
            } else {
                categories.add(category);
            }
            itemService.saveItem(item);

            List<Brand> brands = brandService.getAllBrands();
            List<Category> all_categories = categoryService.getAllCategories();
            model.addAttribute("categories", all_categories);
            model.addAttribute("brands", brands);
            model.addAttribute("item", item);
            return "redirect:/admin_item_details/" + item_id;
        }
        return "redirect:/";
    }

    @PostMapping(value = "/setRole")
    public String setRole(Model model,
                          @RequestParam(name = "user_id", defaultValue = "0") Long user_id,
                          @RequestParam(name = "role_id", defaultValue = "0") Long role_id
    ) {
        Role role = roleService.getRoleById(role_id);
        User user = userService.getUserById(user_id);
        if (role != null && user != null) {
            List<Role> roles = user.getRoles();
            if (roles == null) {
                roles = new ArrayList<>();
            }
            if (roles.contains(role)) {
                roles.remove(role);
            } else {
                roles.add(role);
            }
            userService.saveUser(user);

            ArrayList<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("currentUser", getUserData());
        }
        return "redirect:/admin_users";
    }

    @PostMapping(value = "/addCountry")
    public String addCountry(
            @RequestParam(name = "country_name", defaultValue = "Default name") String name,
            @RequestParam(name = "country_code", defaultValue = "") String country_code
    ) {
        countryService.addCountry(new Country(null, name, country_code));
        return "redirect:/admin_countries";
    }

    @PostMapping(value = "/addRole")
    public String addRole(
            @RequestParam(name = "role_name", defaultValue = "Default name") String role_name,
            @RequestParam(name = "role_description", defaultValue = "") String role_description
    ) {
        roleService.saveRole(new Role(null, role_name, role_description));
        return "redirect:/admin_roles";
    }


    @PostMapping(value = "/editItem")
    public String editItem(@RequestParam(name = "item_id") Long id,
                           @RequestParam(name = "item_name") String name,
                           @RequestParam(name = "item_description") String description,
                           @RequestParam(name = "item_price") Double price,
                           @RequestParam(name = "item_stars") int stars,
                           @RequestParam(name = "item_smallPicURL") String smallPicURL,
                           @RequestParam(name = "item_largePicURL") String largePicURL,
                           @RequestParam(name = "item_inTopPage") Boolean inTopPage,
                           @RequestParam(name = "item_brand_id", defaultValue = "0") Long brand_id
    ) {
        Item item0 = itemService.getItem(id);
        Brand brand = brandService.getBrand(brand_id);
        if (brand != null) {
            Item item = new Item(id, name, description, price, stars, smallPicURL, largePicURL, item0.getAddedDate(), inTopPage, brand, null);
            itemService.saveItem(item);
        }
        return "redirect:/admin_items";
    }

    @PostMapping(value = "/editBrand")
    public String editBrand(@RequestParam(name = "brand_id") Long id,
                            @RequestParam(name = "brand_name") String name,
                            @RequestParam(name = "brand_url") String brand_url,
                            @RequestParam(name = "brand_country_id", defaultValue = "0") Long country_id
    ) {
        Country country = countryService.getCountry(country_id);
        if (country != null) {
            Brand brand = new Brand(id, name, brand_url, country);
            brandService.saveBrand(brand);
        }
        return "redirect:/admin_brands";
    }

    @PostMapping(value = "/editUser")
    public String editUser(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "email") String email,
                           @RequestParam(name = "full_name") String full_name
    ) {
        User user = userService.getUserById(id);
        if (user != null) {
            User user2 = new User();
            user2.setId(id);
            user2.setEmail(email);
            user2.setFull_name(full_name);
            user2.setPassword(user.getPassword());
            user2.setRoles(user.getRoles());
            userService.saveUser(user2);
        }
        return "redirect:/admin_users";
    }

    @GetMapping(value = "/viewPictureOfItem/{url}",produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody byte[] viewPictureOfItem(
            @PathVariable(name = "url") String url)throws IOException {
        String picture_url = viewPathForItem + defaultPicture;
        if (url!=null){
            picture_url = viewPathForItem + url+".jpg";
        }
        InputStream in;
        try {
            ClassPathResource resource = new ClassPathResource(picture_url);
            in = resource.getInputStream();
        }
        catch (Exception e){
            ClassPathResource resource = new ClassPathResource(viewPathForItem + defaultPicture);
            in = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "/viewPhoto/{url}",produces = {MediaType.IMAGE_JPEG_VALUE})
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody byte[] viewProfilePhoto(
            @PathVariable(name = "url") String url)throws IOException {
        String picture_url = viewPath + defaultPicture;
        if (url!=null){
            picture_url = viewPath + url+".jpg";
        }
        InputStream in;
        try {
            ClassPathResource resource = new ClassPathResource(picture_url);
            in = resource.getInputStream();
        }
        catch (Exception e){
            ClassPathResource resource = new ClassPathResource(viewPath + defaultPicture);
            in = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(in);
    }

    @PostMapping(value = "/uploadAvatar")
    @PreAuthorize("isAuthenticated()")
    public String uploadAvatar(Model model,
                           @RequestParam(name = "user_ava") MultipartFile file
    ) {
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            try {
                User currentUser = getUserData();
                assert currentUser != null;
                String picName = DigestUtils.sha1Hex("avatar_"+currentUser.getId()+"_!Picture");
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + picName + ".jpg");
                Files.write(path, bytes);
                currentUser.setPicture_url(picName);
                userService.saveUser(currentUser);
                return "redirect:/profile?success";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/";
    }

    @PostMapping(value = "/updateUserFullName")
    public String editUser(Model model,
                           @RequestParam(name = "id") Long id,
                           @RequestParam(name = "full_name") String full_name
    ) {
        User user = userService.getUserById(id);
        if (user != null) {
            User user2 = new User();
            user2.setId(id);
            user2.setEmail(user.getEmail());
            user2.setFull_name(full_name);
            user2.setPassword(user.getPassword());
            user2.setRoles(user.getRoles());
            userService.saveUser(user2);
        }
        model.addAttribute("error", "noerr");
        return "redirect:/profile";
    }

    @PostMapping(value = "/updateUserPassword")
    public String editUser(Model model,
                           @RequestParam(name = "id") Long id,
                           @RequestParam(name = "old_password") String old_password,
                           @RequestParam(name = "new_password") String new_password,
                           @RequestParam(name = "new_repassword") String new_repassword
    ) {
        User user = userService.getUserById(id);

        boolean result = passwordEncoder.matches(old_password,user.getPassword());
        if (user != null) {
            if (result) {
                if (new_password.equals(new_repassword)) {
                    String encryptedNewPassword = passwordEncoder.encode(new_password);
                    model.addAttribute("error", "noerr");
                    User newUser = new User();
                    newUser.setId(id);
                    newUser.setFull_name(user.getFull_name());
                    newUser.setEmail(user.getEmail());
                    newUser.setRoles(user.getRoles());
                    newUser.setPassword(encryptedNewPassword);
                    userService.saveUser(newUser);
                }
                else {
                    model.addAttribute("error", "Incorrect re password");
                }
            }else {
                model.addAttribute("error", "Incorrect old password");
            }
        }
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    @PostMapping(value = "/editRole")
    public String editRole(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "role_name") String role_name,
                           @RequestParam(name = "role_description") String role_description
    ) {
        Role role = roleService.getRoleById(id);
        if (role != null) {
            role.setRole(role_name);
            role.setDescription(role_description);
            roleService.saveRole(role);
        }
        return "redirect:/admin_roles";
    }

    @PostMapping(value = "/editCategory")
    public String editCategory(@RequestParam(name = "category_id") Long id,
                               @RequestParam(name = "category_name") String name
    ) {
        Category category = categoryService.getCategory(id);
        categoryService.saveCategory(new Category(category.getId(), name));
        return "redirect:/admin_categories";
    }

    @PostMapping(value = "/editCountry")
    public String editCountry(@RequestParam(name = "country_id") Long id,
                              @RequestParam(name = "country_name") String name,
                              @RequestParam(name = "country_code") String code
    ) {
        Country country = countryService.getCountry(id);
        countryService.saveCountry(new Country(country.getId(), name, code));
        return "redirect:/admin_countries";
    }

    @PostMapping(value = "/deleteItem")
    public String deleteItem(@RequestParam(name = "id") Long id) {
        itemService.deleteItemById(id);
        return "redirect:/admin_items";
    }

    @PostMapping(value = "/deleteCategory")
    public String deleteCategory(@RequestParam(name = "id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin_categories";
    }

    @PostMapping(value = "/deleteCountry")
    public String deleteCountry(@RequestParam(name = "id") Long id) {
        for (Brand brand : brandService.getAllBrands()) {
            if (brand.getCountry().getId() == id) {
                brandService.deleteBrandById(brand.getId());
            }
        }
        countryService.deleteCountryById(id);
        return "redirect:/admin_countries";
    }

    @PostMapping(value = "/deleteBrand")
    public String deleteBrand(@RequestParam(name = "id") Long id) {
        for (Item item : itemService.getAllItems()) {
            if (item.getBrand().getId().equals(id)) {
                itemService.deleteItemById(item.getId());
            }
        }
        brandService.deleteBrandById(id);
        return "redirect:/admin_brands";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin_users";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "403";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("currentUser", getUserData());
        model.addAttribute("error", "noerr");
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("error", "noerr");
        return "registration";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    private User getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            User myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }

}
