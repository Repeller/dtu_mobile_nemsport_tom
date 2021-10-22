using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using nemsport_web_api.Models;
using nemsport_web_api.DbBuilds;

namespace nemsport_web_api.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]
    public class EventController : ControllerBase
    {
        public ManageEvent ManageEvents = new ManageEvent();

        // ctor
        public EventController()
        {
            ManageEvents.Events = ManageEvents.LoadEvents();
        }

        /// <summary>
        /// gets all the Event
        /// </summary>
        /// <returns>the list of all Event</returns>
        [HttpGet]
        public IEnumerable<Event> Get()
        {
            return ManageEvents.Events;
        }

        // GET: api/Events/5
        //[HttpGet("{id}", Name = "Get")]
        /// <summary>
        /// get one Event by id
        /// </summary>
        /// <param name="id">the id of the Event you want</param>
        /// <returns>the Event you are looking for</returns>
        [HttpGet]
        [Route("{id}")]
        public Event Get(int id)
        {
            //Item error = new Item(0, "error", "error", 404.404);

            return ManageEvents.Get(id);
        }

        // POST: api/Events
        /// <summary>
        /// post one Event to the list
        /// </summary>
        /// <param name="value">the Event you want to add to the list</param>
        [HttpPost]
        public void Post([FromBody] Event value)
        {
            ManageEvents.Post(value);
        }

        // PUT: api/Events/5
        //[HttpPut("{id}")]
        /// <summary>
        /// put/edit one Event's values and replace them with new ones
        /// </summary>
        /// <param name="id">the id of the Event to put into</param>
        /// <param name="value">the value that will be put into the Event</param>
        [HttpPut]
        [Route("{id}")]
        public void Put(int id, [FromBody] Event value)
        {
            ManageEvents.Put(id, value);
        }

        // DELETE: api/ApiWithActions/5
        /// <summary>
        /// delete one Event from the list
        /// </summary>
        /// <param name="id">the id of the Event you want to delete</param>
        [HttpDelete]
        [Route("{id}")]
        public void Delete(int id)
        {
            ManageEvents.Delete(id);
        }

    }
}
