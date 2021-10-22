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
    public class MemberController : ControllerBase
    {
        public ManageMember ManageMembers = new ManageMember();

        // ctor
        public MemberController()
        {
            ManageMembers.Members = ManageMembers.LoadMembers();
        }

        /// <summary>
        /// gets all the Member
        /// </summary>
        /// <returns>the list of all Member</returns>
        [HttpGet]
        public IEnumerable<Member> Get()
        {
            return ManageMembers.Members;
        }

        // GET: api/Members/5
        //[HttpGet("{id}", Name = "Get")]
        /// <summary>
        /// get one Member by id
        /// </summary>
        /// <param name="id">the id of the Member you want</param>
        /// <returns>the Member you are looking for</returns>
        [HttpGet]
        [Route("{id}")]
        public Member Get(int id)
        {
            //Item error = new Item(0, "error", "error", 404.404);

            return ManageMembers.Get(id);
        }

        // POST: api/Members
        /// <summary>
        /// post one Member to the list
        /// </summary>
        /// <param name="value">the Member you want to add to the list</param>
        [HttpPost]
        public void Post([FromBody] Member value)
        {
            ManageMembers.Post(value);
        }

        // PUT: api/Members/5
        //[HttpPut("{id}")]
        /// <summary>
        /// put/edit one Member's values and replace them with new ones
        /// </summary>
        /// <param name="id">the id of the Member to put into</param>
        /// <param name="value">the value that will be put into the Member</param>
        [HttpPut]
        [Route("{id}")]
        public void Put(int id, [FromBody] Member value)
        {
            ManageMembers.Put(id, value);
        }

        // DELETE: api/ApiWithActions/5
        /// <summary>
        /// delete one Member from the list
        /// </summary>
        /// <param name="id">the id of the Member you want to delete</param>
        [HttpDelete]
        [Route("{id}")]
        public void Delete(int id)
        {
            ManageMembers.Delete(id);
        }
    }
}
