import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.JFileChooser; 
import javax.swing.JOptionPane; 
import java.io.File; 
import java.nio.file.Files;
import java.io.PrintWriter;      
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Sudirwo
 */
public class HitungKataFrame extends javax.swing.JFrame {

    /**
     * Creates new form HitungKataFrame
     */
    public HitungKataFrame() {
        initComponents();
        
        txtArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { hitungSemua(); }
            public void removeUpdate(DocumentEvent e) { hitungSemua(); }
            public void changedUpdate(DocumentEvent e) { hitungSemua(); }
        });
    }
    
    private void hitungSemua() {
        String teks = txtArea.getText().trim();

        // Hitung kata
        int jumlahKata = teks.isEmpty() ? 0 : teks.split("\\s+").length;

        // Hitung karakter
        int jumlahKarakter = teks.length();

        // Hitung kalimat
        int jumlahKalimat = teks.isEmpty() ? 0 : teks.split("[.!?]+").length;

        // Hitung paragraf (dipisah enter)
        int jumlahParagraf = teks.isEmpty() ? 0 : teks.split("\\n+").length;

        lblKata.setText("" + jumlahKata);
        lblKarakter.setText("" + jumlahKarakter);
        lblKalimat.setText("" + jumlahKalimat);
        lblParagraf.setText("" + jumlahParagraf);
    }

    private void cariKata() {
        String teks = txtArea.getText().toLowerCase();
        String cari = txtCari.getText().toLowerCase();

        if (cari.isEmpty()) {
            lblCari.setText("Masukkan kata terlebih dahulu.");
            return;
        }

        int count = 0;
        for (String s : teks.split("\\s+")) {
            if (s.equals(cari)) count++;
        }

        lblCari.setText("Kata ditemukan: " + count + " kali");
    }
    
    private void saveToFile() {
        String teksAsli = txtArea.getText();
        if (teksAsli.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teks kosong!");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Simpan Laporan");

        // 1. Siapkan saran nama file awal
        String baseName = "Laporan_Hitung_Kata";
        String extension = ".txt";
        File fileSaran = new File(baseName + extension);

        // Cek folder default agar saat buka jendela, namanya sudah (1), (2) dst
        int counter = 1;
        while (fileSaran.exists()) {
            fileSaran = new File(baseName + " (" + counter + ")" + extension);
            counter++;
        }
        chooser.setSelectedFile(fileSaran);

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File targetFile = chooser.getSelectedFile();
            String path = targetFile.getAbsolutePath();

            // 2. Pastikan ekstensi .txt
            if (!path.toLowerCase().endsWith(".txt")) {
                path += ".txt";
            }

            // 3. LOGIKA KUNCI: Jika user memilih file yang sudah ada di jendela browse, 
            // kita paksa namanya bertambah angka (1), (2) agar TIDAK menimpa.
            File finalFile = new File(path);
            if (finalFile.exists()) {
                String folder = finalFile.getParent();
                String fileName = finalFile.getName().substring(0, finalFile.getName().lastIndexOf("."));

                int c = 1;
                while (finalFile.exists()) {
                    finalFile = new File(folder + File.separator + fileName + " (" + c + ")" + extension);
                    c++;
                }
            }

            try (PrintWriter out = new PrintWriter(finalFile)) {
                // Header Laporan
                out.println("=== LAPORAN HASIL HITUNG KATA ===");
                out.println("Tanggal: " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                out.println();
                out.println("[HASIL PERHITUNGAN]");

                // Format rapi dengan titik dua sejajar
                out.printf("%-15s : %s%n", "Jumlah Kata", lblKata.getText().replaceAll("[^0-9]", ""));
                out.printf("%-15s : %s%n", "Jumlah Karakter", lblKarakter.getText().replaceAll("[^0-9]", ""));
                out.printf("%-15s : %s%n", "Jumlah Kalimat", lblKalimat.getText().replaceAll("[^0-9]", ""));
                out.printf("%-15s : %s%n", "Jumlah Paragraf", lblParagraf.getText().replaceAll("[^0-9]", ""));

                out.println();
                out.println("[TEKS ASLI]");
                out.println("---------------------------------");
                out.println(teksAsli);
                out.println("---------------------------------");

                JOptionPane.showMessageDialog(this, "Berhasil disimpan sebagai: " + finalFile.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnHitung = new javax.swing.JButton();
        scrollInput = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblKata = new javax.swing.JLabel();
        lblKarakter = new javax.swing.JLabel();
        lblKalimat = new javax.swing.JLabel();
        lblParagraf = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        lblCari = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("Aplikasi Penghitung Kata");
        jPanel1.add(jLabel1);

        jLabel2.setText("Masukkan Teks:");

        btnHitung.setText("Hitung");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        scrollInput.setViewportView(txtArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHitung)
                    .addComponent(jLabel2)
                    .addComponent(scrollInput, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollInput, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHitung)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Hasil Real Time");
        jPanel3.add(jLabel7);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Jumlah Kata");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 70, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Jumlah Karakter");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Jumlah Kalimat");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 90, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Jumlah Paragraf");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 100, -1));

        lblKata.setText("-");
        jPanel4.add(lblKata, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        lblKarakter.setText("-");
        jPanel4.add(lblKarakter, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        lblKalimat.setText("-");
        jPanel4.add(lblKalimat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        lblParagraf.setText("-");
        jPanel4.add(lblParagraf, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        jLabel11.setText("Cari Kata");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, 20));
        jPanel4.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 94, -1));

        btnCari.setText("Search");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        jPanel4.add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        lblCari.setText("-");
        jPanel4.add(lblCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 250, -1));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel4.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 130, 70, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 23, Short.MAX_VALUE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        // TODO add your handling code here:
        hitungSemua();
    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        cariKata();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        saveToFile();
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HitungKataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HitungKataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HitungKataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HitungKataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HitungKataFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblKalimat;
    private javax.swing.JLabel lblKarakter;
    private javax.swing.JLabel lblKata;
    private javax.swing.JLabel lblParagraf;
    private javax.swing.JScrollPane scrollInput;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
