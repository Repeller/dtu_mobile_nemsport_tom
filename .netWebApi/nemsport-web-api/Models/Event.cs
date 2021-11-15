using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace nemsport_web_api.Models
{
    public class Event
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public DateTime Created { get; set; }
        public DateTime EventStart { get; set; }
        public string Descript { get; set; }
        public int MaxPlayers { get; set; }
        public int OnlyPaid { get; set; }
        public int Fk_member_id { get; set; }

        public Event()
        { }

        public Event(int id, string title, DateTime created, DateTime eventStart, string descript, int maxPlayers, int onlyPaid, int fk_member_id)
        {
            Id = id;
            Title = title;
            Created = created;
            EventStart = eventStart;
            Descript = descript;
            MaxPlayers = maxPlayers;
            OnlyPaid = onlyPaid;
            Fk_member_id = fk_member_id;
        }

        public override string ToString()
        {
            return $"{nameof(Id)}: {Id}, {nameof(Title)}: {Title}, {nameof(Created)}: {Created}, {nameof(EventStart)}: {EventStart}, {nameof(Descript)}: {Descript}, {nameof(MaxPlayers)}: {MaxPlayers}, {nameof(OnlyPaid)}: {OnlyPaid}, {nameof(Fk_member_id)}: {Fk_member_id}";
        }
    }
}
