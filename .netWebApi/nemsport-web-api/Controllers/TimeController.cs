using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using nemsport_web_api.Models;
using nemsport_web_api.DbBuilds;

namespace nemsport_web_api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TimeController : ControllerBase
    {
        public ManageTime ManageTimes = new ManageTime();

        // ctor
        public TimeController()
        {
            ManageTimes.Times = ManageTimes.LoadTimes();
        }

        /// <summary>
        /// gets all the Time
        /// </summary>
        /// <returns>the list of all Time</returns>
        [HttpGet]
        public IEnumerable<Time> Get()
        {
            return ManageTimes.Times;
        }

        // GET: api/Times/5
        //[HttpGet("{id}", Name = "Get")]
        /// <summary>
        /// get one Time by id
        /// </summary>
        /// <param name="id">the id of the Time you want</param>
        /// <returns>the Time you are looking for</returns>
        [HttpGet]
        [Route("{id}")]
        public Time Get(int id)
        {
            //Item error = new Item(0, "error", "error", 404.404);

            return ManageTimes.Get(id);
        }

        // POST: api/Times
        /// <summary>
        /// post one Time to the list
        /// </summary>
        /// <param name="value">the Time you want to add to the list</param>
        [HttpPost]
        public void Post([FromBody] Time value)
        {
            ManageTimes.Post(value);
        }

        // PUT: api/Times/5
        //[HttpPut("{id}")]
        /// <summary>
        /// put/edit one Time's values and replace them with new ones
        /// </summary>
        /// <param name="id">the id of the Time to put into</param>
        /// <param name="value">the value that will be put into the Time</param>
        [HttpPut]
        [Route("{id}")]
        public void Put(int id, [FromBody] Time value)
        {
            ManageTimes.Put(id, value);
        }

        // DELETE: api/ApiWithActions/5
        /// <summary>
        /// delete one Time from the list
        /// </summary>
        /// <param name="id">the id of the Time you want to delete</param>
        [HttpDelete]
        [Route("{id}")]
        public void Delete(int id)
        {
            ManageTimes.Delete(id);
        }
    }
}
