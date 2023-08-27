import Objects.ClientProduct;
import org.bukkit.Bukkit;

public class Logger {

    public static void PrintTAIGO(){
        Bukkit.getLogger().info("\n" +
                "────────────────────────────────────────────────────────────────────────\n" +
                "─██████████████─██████████████─██████████─██████████████─██████████████─\n" +
                "─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─\n" +
                "─██████░░██████─██░░██████░░██─████░░████─██░░██████████─██░░██████░░██─\n" +
                "─────██░░██─────██░░██──██░░██───██░░██───██░░██─────────██░░██──██░░██─\n" +
                "─────██░░██─────██░░██████░░██───██░░██───██░░██─────────██░░██──██░░██─\n" +
                "─────██░░██─────██░░░░░░░░░░██───██░░██───██░░██──██████─██░░██──██░░██─\n" +
                "─────██░░██─────██░░██████░░██───██░░██───██░░██──██░░██─██░░██──██░░██─\n" +
                "─────██░░██─────██░░██──██░░██───██░░██───██░░██──██░░██─██░░██──██░░██─\n" +
                "─────██░░██─────██░░██──██░░██─████░░████─██░░██████░░██─██░░██████░░██─\n" +
                "─────██░░██─────██░░██──██░░██─██░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─\n" +
                "─────██████─────██████──██████─██████████─██████████████─██████████████─\n" +
                "────────────────────────────────────────────────────────────────────────\n");
    }

    public static void Info(String message){
        Bukkit.getLogger().info(message);
    }

    public static void Warning(String message){
        Bukkit.getLogger().warning(message);
    }

    public static void NotFound()
    {
        PrintTAIGO();
        Logger.Info("Check the correctness of the data entered in config.yml. App_id or token does not match the data stored on the TAIGO servers\n" +
                "Our contacts:\n" +
                "Site: https://taigo.xyz\n" +
                "Fiverr: https://fiverr.taigo.xyz\n" +
                "Kwork: https://kwork.taigo.xyz\n" +
                "VK: https://vk.com/taigostudio");
        Bukkit.getServer().shutdown();
    }

    public static void ConnectError()
    {
        PrintTAIGO();
        Logger.Info("We could not connect to the TAIGO.ID server to check the originality of the server part");
        Bukkit.getServer().shutdown();
    }

    public static void Blocked(){
        PrintTAIGO();
        Logger.Info("The authorization was rejected due to the blocking of your TAIGO ID account.\n" +
                "The reason for blocking is a violation of our user agreement:\n" +
                "https://vk.com/topic-166798462_48847954\n\n" +
                "Made with TAIGO.ZCore\n" +
                "© 2018–{DateTime.UtcNow.Year} «TAIGO»\n\n" +
                "Our contacts:\n" +
                "Site: https://taigo.xyz\n" +
                "Fiverr: https://fiverr.taigo.xyz\n" +
                "Kwork: https://kwork.taigo.xyz\n" +
                "VK: https://vk.com/taigostudio");
        Bukkit.getServer().shutdown();
    }

    public static void SuccessLogin(ClientProduct product){
        PrintTAIGO();
        Info("You have successfully logged into your account TAIGO ID!" +
                "\n\nDetails of your order:\n" +
                "Product name:" + product.name + "\n" +
                "Warranty start date: " + product.warrantyDate + "\n" +
                "Warranty (in days):" + product.warrantyDays);
        if(product.warrantyIsRevoked)
            Warning("We have withdrawn the warranty for this product. Most likely you have violated our user agreement");

        if(product.InWork)
            Warning("The product is still in development! This is a test version, there may be bugs in it. Contact us if you find a bug");
    }
}
