package com.example.myfirstapp.models;

import java.io.InputStream;
import java.sql.*;

//packages for supabase client
import io.supabase.StorageClient;
import io.supabase.api.IStorageFileAPI;
import io.supabase.data.bucket.BucketUpdateOptions;
import io.supabase.data.file.*;
import io.supabase.utils.MessageResponse;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
//end

//http packages for downloading via http
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
//end

public class DataBase {
    private String url = "jdbc:postgresql://db.fauokmrzqpowzdiqqxxg.supabase.co:5432/postgres";
    private String user = "postgres";
    private String password = "palakapapoy";


    /**
     * This method establishes a connection with the POSTGRES database
     * @return Returns an object of connection
     */
    public Connection createConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user, password);
            return connection;
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method fetches the user from the database using the parameter username
     * this returns an object of User
     * @param username username to fetch in the database
     * @return object of user
     */
    public User fetchUser(String username){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_details WHERE username = '"+username+"'");
            if(rs.next()){
                //Gets data from ResultSet and segregates them into respective variables
                String fetchedUsername = rs.getString(1);
                String fetchedPassword = rs.getString(2);
                int fetchedUserID = rs.getInt(3);
                String fetchedFName = rs.getString(4);
                String fetchedMName = rs.getString(5);
                String fetchedLName = rs.getString(6);
                // constructs and returns a new object of user based on the fetched data
                return new User(fetchedUsername, fetchedPassword, true,fetchedFName, fetchedMName, fetchedLName);
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method fetches the contact details of a given user using the username
     * @param username  User to fetcch contact details of
     * @return  Returns an object of ContactDetails
     */
    public ContactDetails fetchContactDetails(String username){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contact_details WHERE username = '"+username+"'");
            int fetchedID = rs.getInt(1);
            String fetchedUsername = rs.getString(2);
            String fetchedEmail = rs.getString(3);
            String fetchedAddress = rs.getString(4);
            String fetchedNumber = rs.getString(5);
            ContactDetails cd = new ContactDetails(fetchedUsername, fetchedEmail,fetchedNumber,fetchedAddress);
            cd.setId(fetchedID);
            return cd;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fetches the travel plan given a search key and column
     * @param columnName    Column to search at
     * @param searchKey     Search key to be used at column
     * @return  Returns an object of TravelPlan
     */
    public TravelPlan fetchTravelPlan(String columnName, String searchKey){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM travel_plan WHERE "+columnName+" = '"+searchKey+"'");
            String fetchedTitle = rs.getString(3);
            String fetchedReviews = rs.getString(4);
            String fetchedAuthor = rs.getString(2);
            String fetchedDuration = rs.getString(5);
            String fetchedEstimatedCost = rs.getString(6);
            String fetchedDescription = rs.getString(7);
            String fetchedDestinations = rs.getString(8);
            TravelPlan fetchedPlan = new TravelPlan(fetchedTitle, null, fetchedAuthor,fetchedDuration, fetchedEstimatedCost,fetchedDescription,fetchedDestinations);
            fetchedPlan.setReviews(fetchedReviews);
            fetchedPlan.setPost_id(rs.getInt(1));
            return fetchedPlan;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method searches a given table using given column and search key
     * @param table Table to search
     * @param column    Column to search
     * @param searchKey Search key to be used for searching
     * @return  Returns the id of the row
     */
    public int search(String table, String column, String searchKey){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM "+table+" WHERE "+column+" = '"+searchKey+"'");
            return rs.getInt(1);
        }catch(SQLException search){
            search.printStackTrace();
        }

        return -1;
    }

    /**
     * This method allows you to upload images to the content delivery
     * @param localPath Local path of the image you want to upload
     * @param remotePath    Remote path of where you want to upload the image
     * @return  Returns a boolean value if the image has been successfully uploaded
     */
    public boolean uploadImage(String localPath, String remotePath){
        String url = "https://fauokmrzqpowzdiqqxxg.supabase.co/storage/v1/";
        String serviceToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZhdW9rbXJ6cXBvd3pkaXFxeHhnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDE1ODcyNTYsImV4cCI6MjAxNzE2MzI1Nn0.3GYnldygSO7wCrKZVHkQyviW0LVwS6KdPpAqIVa-EcE";

        StorageClient storageClient = new StorageClient(serviceToken, url);

        IStorageFileAPI fileAPI = storageClient.from("images");
        try {
            // We call .get here to block the thread and retrieve the value or an exception.
            // Pass the file path in supabase storage and pass a file object of the file you want to upload.
            FilePathResponse response = fileAPI.upload("test/image.png", new File("src/my-secret-image/image.png")).get();

            // Generate a public url (The link is only valid if the bucket is public).
            //fileAPI.getPublicUrl("my-secret-image/image.png", new FileDownloadOption(false), new FileTransformOptions(500, 500, ResizeOption.COVER, 50, FormatOption.NONE));

            // Create a signed url to download an object in a private bucket that expires in 60 seconds, and will be downloaded instantly on link as "my-image.png"
            //fileAPI.getSignedUrl("my-secret-image/image.png", 60, new FileDownloadOption("my-image.png"), null);

            // Download the file
            //fileAPI.download("my-secret-image/image.png", null);
            System.out.println("Uploaded");
            return true;

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method allows you to download an image given the remote path of the image as well as its bucket,
     * and the name of the output file
     * @param remotePath    Path of the file in the remote server
     * @param bucket    Bucket of the path in the server
     * @param fileOutputPath    Specify to what file you would want the output to go through
     * @return
     */
    public boolean downloadImage(String remotePath, String bucket,String fileOutputPath){
        String url = "https://fauokmrzqpowzdiqqxxg.supabase.co/storage/v1/object/public/";
        url+=bucket+remotePath+"?download="+fileOutputPath;
        try{
            //New object of url
            URL urlOb = new URL(url);

            //Opens a connection to the url object
            URLConnection urlConnection = urlOb.openConnection();

            InputStream inputStream = urlConnection.getInputStream();

            FileOutputStream fileOutputStream = new FileOutputStream(fileOutputPath);

            // Read from the input stream and write to the output stream
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            // Close streams
            inputStream.close();
            fileOutputStream.close();


            System.out.println("File downloaded successfully!");

            return true;

        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

}
