using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace nemsport_web_api.Models
{
    public class Member
    {
        // props
        public int Id { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public int MemberType { get; set; }

        // constructor
        public Member()
        { 
        }

        public Member(int id, string username, string password, int memberType) 
        {
            Id = id;
            Username = username;
            Password = password;
            MemberType = memberType;
        }

        public override string ToString()
        {
            return $"{nameof(Id)}: {Id}, {nameof(Username)}: {Username}, {nameof(Password)}: {Password}, {nameof(MemberType)}: {MemberType}";
        }
    }
}
