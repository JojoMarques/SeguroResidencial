package br.com.tokio.model;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

public class Impressora {

    // Método que inicia o trabalho de impressão
    public void iniciarImpressao(JPanel painel) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                // Converter o Graphics para Graphics2D
                Graphics2D g2d = (Graphics2D) graphics;

                // Configura a posição de início da impressão
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                // Escala o conteúdo se necessário
                double scaleX = pageFormat.getImageableWidth() / painel.getWidth();
                double scaleY = pageFormat.getImageableHeight() / painel.getHeight();
                double scale = Math.min(scaleX, scaleY);
                g2d.scale(scale, scale);

                // Desenha o painel na área de impressão
                painel.printAll(g2d);

                return Printable.PAGE_EXISTS;
            }
        });

        // Exibe a caixa de diálogo de impressão
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
