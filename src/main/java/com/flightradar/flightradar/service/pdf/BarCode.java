package com.flightradar.flightradar.service.pdf;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
class BarCode {

    void createBarCode(String fileName) {
        final String path = "C:\\Users\\Krystian\\Desktop\\Flight Radar\\flight-radar\\src\\main\\resources\\pdf\\";
        try {
            Code39Bean bean39 = new Code39Bean();
            final int dpi = 160;

            bean39.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
            bean39.doQuietZone(false);

            File outputFile = new File(path + fileName + ".jpg");
            FileOutputStream out = new FileOutputStream(outputFile);
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            bean39.generateBarcode(canvas, fileName);
            canvas.finish();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}









