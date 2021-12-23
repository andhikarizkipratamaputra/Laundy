package com.example.demo.Controller;

import com.example.demo.Model.AreaParkir;
import com.example.demo.Model.GedungParkir;
import com.example.demo.Model.Kendaraan;
import com.example.demo.Model.Parking;
import com.example.demo.Repository.AreaParkirRepository;
import com.example.demo.Repository.GedungParkirRepository;
import com.example.demo.Repository.KendaraanRepository;
import com.example.demo.Repository.ParkingRepository;
import com.example.demo.Util.QRCodeGenerator;
import com.example.demo.Util.Token;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("parking")
public class ParkingController {
    @Autowired
    KendaraanRepository kendaraanRepository;

    @Autowired
    AreaParkirRepository areaParkirRepository;

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    GedungParkirRepository gedungParkirRepository;


    @GetMapping()
    public ModelAndView index(ModelAndView mView) {
        List<Parking> parkingList = parkingRepository.findAll();
        mView.addObject("parkingList", parkingList);
        mView.setViewName("pages/parking/index");
        return mView;
    }


    @GetMapping("/tambah")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        List<GedungParkir> gedungParkirList = gedungParkirRepository.findAll();
        model.addAttribute("gedungParkirList", gedungParkirList);

        List<Kendaraan> kendaraanList = kendaraanRepository.findAll();
        model.addAttribute("kendaraanList", kendaraanList);


        // put default data for ${object} on form is there's no default
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new Parking());
        }
        return "pages/parking/add";
    }

    @GetMapping("/parkirpenuh")
    public ModelAndView parkirPenuh(ModelAndView mView){
        mView.setViewName("pages/parking/parkirpenuh");
        return mView;
    }





    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") Parking parking,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("idGedungParkir") Long idGedungParkir,
                               @RequestParam("idKendaraan") Long idKendaraan,
                               @RequestParam("waktuBookingString") String waktuBookingString) throws ParseException, IOException, WriterException {

        Kendaraan kendaraan = kendaraanRepository.findById(idKendaraan).get();
        GedungParkir gedungParkir = gedungParkirRepository.findById(idGedungParkir).get();
        List<AreaParkir> areaParkirKosongList = areaParkirRepository.findSatuAreaParkirKosongByGedungParkir(gedungParkir);

        if(areaParkirKosongList.size()==0){
            //Parkiran penuh
            mView.setViewName("redirect:/parking/parkirpenuh");
            return mView;
        }
        //2019-06-16T20:00
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date waktuBooking = sdf.parse(waktuBookingString);

        parking.setKendaraan(kendaraan);
        AreaParkir areaParkir = areaParkirKosongList.get(0);
        parking.setAreaParkir(areaParkir);
        parking.setWaktuBooking(waktuBooking);
        String token = Token.randomAlphaNumeric(10);
        parking.setKodeBooking(token);

        areaParkir.setAvailable(false);
        areaParkirRepository.save(areaParkir);
        parkingRepository.save(parking);

        /*
        Generate QR CODE
         */
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        qrCodeGenerator.generateQRCodeImage(token);
        mView.setViewName("redirect:/parking");
        return mView;
    }
}