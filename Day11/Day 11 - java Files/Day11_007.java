import java.io.*;
public class Day11_007
{
    public static void main(String args[])
    {
        try
        {
            FileInputStream file1 = new FileInputStream("FileName01.txt");
            FileInputStream file2 = new FileInputStream("FileName02.txt");
            SequenceInputStream file3 = new SequenceInputStream(file1, file2);

           // BufferedInputStream br1 = new BufferedInputStream(file3);
          // BufferedOutputStream br2 = new BufferedOutputStream(System.out);

            FileOutputStream br2 = new FileOutputStream("FileName06");

            int ch;
            while((ch = file3.read())!=-1)
            {
                br2.write((char)ch);
            }
            file3.close();
            br2.close();
            file1.close();
            file2.close();
            System.out.println("Merge Two File Sucessfully ");
        }
        catch(IOException e)
        {
            System.out.println("Sorry..!! File Not Found...!!!");
        }
    }
}
