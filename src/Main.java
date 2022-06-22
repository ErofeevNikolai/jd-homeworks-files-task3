import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        // 1. Проводим распаковку архив в папке
        openZip("D://коля//JAVA//ДЗ//JavaCore//jd-homeworks-files-task1//Games//savegames//zip.zip",
                "D://коля//JAVA//ДЗ//JavaCore//jd-homeworks-files-task1//Games//savegames");

        // 2. Cчитывание и десериализациz одного из разархивированных файлов
        GameProgress save = openProgress("D://коля//JAVA//ДЗ//JavaCore//jd-homeworks-files-task1//Games//savegames//save1.txt");
        System.out.println(save.toString());
    }

    /*
    Метод разархивирующий архив archiveFile в папку fileFolder
    */
    public static void openZip(String archiveFile, String fileFolder) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(archiveFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(fileFolder + "//" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*
    Метод производящий десериализацию файла fileFolder
     */
    public static GameProgress openProgress(String fileFolder) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(fileFolder);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}
