curl -XPOST localhost:8019/register -H "content-type:application/json" -d '
{
  "phoneNumber":"085847452017",
  "password":"rioters7",
  "email":"kiditzbastara@gmail.com",
  "userAuthorityList":[
    {"authority":"TEACHER"}
  ]     
}

'
