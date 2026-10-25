function valid()
            {
              p1=document.getElementById("pwd1").value;
              p2=document.getElementById("pwd2").value;
              
              if(p1===p2)
              {
                return true;  
              }
              else
              {
                  document.getElementById("one").innerHTML="Password and confirn password not match";
                 return false; 
              }
              
            }

