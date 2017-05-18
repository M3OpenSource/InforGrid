REM see  https://m3ideas.org/2017/05/15/building-an-infor-grid-lab-part-3/




cd \Infor\Grid\

REM using the Grid console tool

REM Create Grid key material
java ^
 -cp resources\grid-core.jar;resources\bcprov-jdk16.jar;resources\bcmail-jdk16.jar ^
 com.lawson.grid.security.Certificates ^
 -create=gridcert ^
 -gridname Grid ^
 -gridpassword password123 ^
 -gridkeystore secure

REM Create Host key material
java ^
 -cp resources\grid-core.jar;resources\bcprov-jdk16.jar;resources\bcmail-jdk16.jar ^
 com.lawson.grid.security.Certificates ^
 -create=hostcert ^
 -gridname Grid ^
 -gridpassword password123 ^
 -hostname localhost ^
 -gridkeystore secure ^
 -hostkeystore secure ^
 -role grid-admin ^
 -address localhost ^
 -address ::1 ^
 -address 127.0.0.1 ^
 -address example.com ^
 -unresolved

REM Create symmetric key material
java ^
 -cp resources\grid-core.jar;resources\bcprov-jdk16.jar;resources\bcmail-jdk16.jar ^
 com.lawson.grid.security.Certificates ^
 -create=symkey ^
 -gridname Grid ^
 -gridkeystore secure ^
 -gridpassword password123 ^
 -symkeypath secure ^
 -hostkeystore secure ^
 -hostname localhost




REM using the Java keytool

REM Create Grid key material
keytool ^
 -genkeypair ^
 -keyalg RSA ^
 -keysize 2048 ^
 -sigalg SHA256WITHRSA ^
 -dname cn=Grid ^
 -ext BasicConstraints=ca:true,pathlen:1 ^
 -ext KeyUsage=digitalSignature,keyCertSign ^
 -ext ExtendedkeyUsage=serverAuth,codeSigning,clientAuth ^
 -validity 90 ^
 -keypass password123 ^
 -keystore secure\Grid.ks ^
 -storepass password123
 
keytool ^
 -exportcert ^
 -file secure\Grid.der ^
 -keystore secure\Grid.ks ^
 -storepass password123
keytool ^
 -changealias ^
 -alias mykey ^
 -destalias grid_key ^
 -keypass password123 ^
 -keystore secure\Grid.ks ^
 -storepass password123
keytool ^
 -noprompt ^
 -importcert ^
 -alias mykey ^
 -file secure\Grid.der ^
 -keypass password123 ^
 -keystore secure\Grid.ks ^
 -storepass password123
keytool ^
 -changealias ^
 -alias mykey ^
 -destalias grid_cert ^
 -keypass password123 ^
 -keystore secure\Grid.ks ^
 -storepass password123

REM Create Host key material
keytool ^
 -genkey ^
 -alias localhost_key ^
 -keyalg RSA ^
 -keysize 2048 ^
 -sigalg SHA256WITHRSA ^
 -dname cn=localhost ^
 -ext 1.3.6.1.4.1.10105.237.0.2=300C0C0A677269642D61646D696E ^
 -ext 1.3.6.1.4.1.10105.237.0.3=0C0653595354454D ^
 -ext 1.3.6.1.4.1.10105.237.0.4=30280C096C6F63616C686F73740C033A3A310C093132372E302E302E310C0B6578616D706C652E636F6D ^
 -validity 90 ^
 -keypass password123 ^
 -keystore secure\server.ks ^
 -storepass password123
keytool ^
 -certreq ^
 -alias localhost_key ^
 -keyalg SHA256WITHRSA ^
 -file secure\server.csr.txt ^
 -keystore secure\server.ks ^
 -storepass password123
keytool ^
 -gencert ^
 -infile secure\server.csr.txt ^
 -outfile secure\server.der ^
 -keystore secure\Grid.ks ^
 -storepass password123 ^
 -alias grid_key ^
 -ext BC=0
keytool ^
 -importcert ^
 -noprompt ^
 -trustcacerts ^
 -alias grid_key ^
 -file secure\Grid.der ^
 -keystore secure\server.ks ^
 -storepass password123
keytool ^
 -importcert ^
 -trustcacerts ^
 -alias localhost_key ^
 -file secure\server.der ^
 -keystore secure\server.ks ^
 -storepass password123
echo | set /p="password123" > secure\server.pw
