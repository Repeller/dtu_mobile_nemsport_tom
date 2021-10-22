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
    public class EventMemberController : ControllerBase
    {
        public ManageEventMember ManageEventMembers = new ManageEventMember();

        // ctor
        public EventMemberController()
        {
            ManageEventMembers.EventMembers = ManageEventMembers.LoadEventMembers();
        }

        /// <summary>
        /// gets all the EventMember
        /// </summary>
        /// <returns>the list of all EventMember</returns>
        [HttpGet]
        public IEnumerable<EventMember> Get()
        {
            return ManageEventMembers.EventMembers;
        }

        // GET: api/EventMembers/5
        //[HttpGet("{id}", Name = "Get")]
        /// <summary>
        /// get one EventMember by id
        /// </summary>
        /// <param name="id">the id of the EventMember you want</param>
        /// <returns>the EventMember you are looking for</returns>
        [HttpGet]
        [Route("{id}")]
        public EventMember Get(int id)
        {
            //Item error = new Item(0, "error", "error", 404.404);

            return ManageEventMembers.Get(id);
        }

        // POST: api/EventMembers
        /// <summary>
        /// post one EventMember to the list
        /// </summary>
        /// <param name="value">the EventMember you want to add to the list</param>
        [HttpPost]
        public void Post([FromBody] EventMember value)
        {
            ManageEventMembers.Post(value);
        }

        // PUT: api/EventMembers/5
        //[HttpPut("{id}")]
        /// <summary>
        /// put/edit one EventMember's values and replace them with new ones
        /// </summary>
        /// <param name="id">the id of the EventMember to put into</param>
        /// <param name="value">the value that will be put into the EventMember</param>
        [HttpPut]
        [Route("{id}")]
        public void Put(int id, [FromBody] EventMember value)
        {
            ManageEventMembers.Put(id, value);
        }

        // DELETE: api/ApiWithActions/5
        /// <summary>
        /// delete one EventMember from the list
        /// </summary>
        /// <param name="id">the id of the EventMember you want to delete</param>
        [HttpDelete]
        [Route("{id}")]
        public void Delete(int id)
        {
            ManageEventMembers.Delete(id);
        }
    }
}
