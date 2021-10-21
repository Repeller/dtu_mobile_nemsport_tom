using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace nemsport_web_api.Models
{
    public class Time
    {
        int Id { get; set; }
        DateTime TimeStart { get; set; }
        DateTime TimeEnd { get; set; }

        public Time() 
        { 
        }

        public Time(int id, DateTime timeStart, DateTime timeEnd)
        {
            Id = id;
            TimeStart = timeStart;
            TimeEnd = timeEnd;
        }

        public override string ToString()
        {
            return $"{nameof(Id)}: {Id}, {nameof(TimeStart)}: {TimeStart}, {nameof(TimeEnd)}: {TimeEnd}";
        }

    }
}
