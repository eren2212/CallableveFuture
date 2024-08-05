
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

   
    public static void main(String[] args) {
        
       ExecutorService executorService = Executors.newFixedThreadPool(1);
       
       
       /*executorService.submit(new Runnable() {
           @Override
           public void run() {
               Random random = new Random();
               
               System.out.println("Thread basliyor ...");
               int sure = random.nextInt(1000)+2000;
               
               try {
                   Thread.sleep(sure);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               System.out.println("Thread çıkıyor...");
           }
       });
       
       executorService.shutdown();*/
       
       //burada integer değer döndürebiliyoruz
        Future<Integer>  future = executorService.submit(new Callable<Integer>(){
           @Override
           public Integer call() throws Exception {
              
               Random random = new Random();
               
               System.out.println("Thread basliyor ...");
               int sure = random.nextInt(1000)+2000;
               
               if(sure>2500){
                   throw new IOException("Thread çok uzun süre uyudu...");
               }
               
               try {
                   Thread.sleep(sure);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               System.out.println("Thread çıkıyor...");
               
               return sure;
           }
           
       });
       
       executorService.shutdown();
       
       
        try {
            //süreyi döndürebilmek için future interfaceinden bir referans oluşturmamız laızm.
            System.out.println("Süre :"+future.get());
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            System.out.println(ex);
        }
    }
    
}
