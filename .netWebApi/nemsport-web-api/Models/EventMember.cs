using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace nemsport_web_api.Models
{
    public class EventMember
    {
        public int Id { get; set; }
        public int Fk_member_id { get; set; }
        public int Fk_event_id { get; set; }

        EventMember()
        {

        }

        EventMember(int id, int fk_member_id, int fk_event_id)
        {
            Id = id;
            Fk_member_id = fk_member_id;
            Fk_event_id = fk_event_id;
        }

        public override string ToString()
        {
            return $"{nameof(Id)}: {Id}, {nameof(Fk_member_id)}: {Fk_member_id}, {nameof(Fk_event_id)}: {Fk_event_id}";

        }

    }
}
