package com.example.demo.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.OutputStream;
import com.example.demo.DTOs.Device;
import com.example.demo.DTOs.Fiche;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class FicheGeneratorServiceImpl implements FicheGeneratorService {

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public byte[] generateMaintenanceFiche(Fiche fiche) {
        try {
            // Dossier de stockage des fiches
            String ficheFolderPath = "src/main/resources/fiches/";
            File ficheFolder = new File(ficheFolderPath);
            if (!ficheFolder.exists()) {
                boolean created = ficheFolder.mkdirs();
                if (!created) {
                    System.err.println("Échec de la création du répertoire 'fiches'");
                    return null;
                }
            }
    
            // Créez un contexte Thymeleaf pour la fiche de maintenance
            Context context = new Context();
            context.setVariable("fiche", fiche);
    
            // Générez le contenu HTML de la fiche de maintenance
            String htmlContent = templateEngine.process("Fiche", context);
    
            // Créez un flux de sortie pour le PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
            // Créez un moteur de rendu PDF
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream, false); // false pour ne pas fermer le document
    
            // Ajoutez les détails des autres appareils à la même fiche
            for (int i = 1; i < fiche.getDevices().size(); i++) {
                context.setVariable("device", fiche.getDevices().get(i));
                htmlContent = templateEngine.process("Fiche", context);
                renderer.setDocumentFromString(htmlContent);
                renderer.layout();
                renderer.writeNextDocument(); // Écriture du document suivant
            }
    
            // Finalisez le PDF
            renderer.finishPDF();
    
            // Écrivez le contenu HTML dans le fichier PDF
            File pdfFile = new File(ficheFolder, "fiche" + fiche.getId() + ".pdf");
            try (OutputStream fileOutputStream = new FileOutputStream(pdfFile)) {
                outputStream.writeTo(fileOutputStream);
            }
    
            // Retournez le PDF généré sous forme de tableau de bytes
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérez l'exception de manière appropriée
            return null;
        }
    }
}    