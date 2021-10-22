using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;
using nemsport_web_api.Models;

namespace nemsport_web_api.DbBuilds
{
    public class ManageEventMember
    {
        private string _jsonString = System.IO.File.ReadAllText("loginInfo.json");



        // fields
        private const string _connectionString = "Server=tcp:nemsport-server.database.windows.net,1433; " +
                                                "Initial Catalog=nemsport_db; " +
                                                "Persist Security Info=False;User " +
                                                "ID=nemsport-admin; " +
                                               "Password={your_password}; " +
                                                "MultipleActiveResultSets=False; " +
                                                "Encrypt=True;TrustServerCertificate=False; " +
                                                "Connection Timeout=30;";

        private const string GET_ALL = "select * from dbo.EventMember";
        private const string GET_ONE = "select * from dbo.EventMember WHERE Id = @ID";
        private const string POST_ONE = @"insert into dbo.EventMember 
                                        (id, fk_member_id, fk_event_id) 
                                        VALUES 
                                        (@ID, @FK_MEMBER_ID, @FK_EVENT_ID)";

        private const string PUT_ONE = @"UPDATE dbo.EventMember SET 
                                                id = @ID, 
                                                fk_member_id = @FK_MEMBER_ID, 
                                                fk_event_id = @FK_EVENT_ID
                                                WHERE Id = @ID";

        private const string DELETE_ONE = "DELETE FROM dbo.EventMember WHERE Id = @ID";


        public IEnumerable<EventMember> EventMembers { get; set; }

        // maybe this part could be deleted, I will test it later
        public IEnumerable<EventMember> Get()
        {
            return EventMembers;
        }

        /// <summary>
        /// reads the data from the reader and returns a EventMember obj
        /// </summary>
        /// <param name="reader">the reader in use</param>
        /// <returns>the EventMember obj from the reader</returns>
        protected EventMember ReadNextElement(SqlDataReader reader)
        {
            EventMember tempEventMember = new EventMember();

            tempEventMember.Id = reader.GetInt32(0);
            tempEventMember.Fk_member_id = reader.GetInt32(1);
            tempEventMember.Fk_event_id = reader.GetInt32(2);


            return tempEventMember;
        }

        /// <summary>
        /// used to load everything into the list
        /// </summary>
        /// <returns>the list of all EventMember</returns>
        public IEnumerable<EventMember> LoadEventMembers()
        {
            List<EventMember> tempEventMember = new List<EventMember>();
            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(GET_ALL, Conn))
            {
                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    EventMember value = ReadNextElement(reader);
                    tempEventMember.Add(value);
                }
                reader.Close();
                Conn.Close();
            }

            return tempEventMember;
        }

        public EventMember Get(int id)
        {
            EventMember tempEventMember = new EventMember();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(GET_ONE, Conn))
            {
                cmd.Parameters.AddWithValue("@ID", id);
                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    EventMember value = ReadNextElement(reader);
                    if (value.Id == id)
                    {
                        tempEventMember = value;
                        break;
                    }
                }
                reader.Close();
                Conn.Close();
            }

            return tempEventMember;
        }

        public void Post(EventMember value)
        {
            EventMember tempEventMember = new EventMember();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(POST_ONE, Conn))
            {

                // (@ID, @FK_MEMBER_ID, @FK_EVENT_ID)

                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@ID", value.Id);
                cmd.Parameters.AddWithValue("@FK_MEMBER_ID", value.Fk_member_id);
                cmd.Parameters.AddWithValue("@FK_EVENT_ID", value.Fk_event_id);


                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempEventMember = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                EventMembers = LoadEventMembers();
            }
        }

        public void Put(int id, EventMember value)
        {
            EventMember tempEventMember = new EventMember();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(PUT_ONE, Conn))
            {
                // (@ID, @FK_MEMBER_ID, @FK_EVENT_ID)

                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@ID", id);
                cmd.Parameters.AddWithValue("@FK_MEMBER_ID", value.Fk_member_id);
                cmd.Parameters.AddWithValue("@FK_EVENT_ID", value.Fk_event_id);

                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempEventMember = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                EventMembers = LoadEventMembers();
            }
        }

        public void Delete(int id)
        {
            EventMember tempEventMember = new EventMember();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(DELETE_ONE, Conn))
            {
                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@ID", id);

                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempEventMember = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                EventMembers = LoadEventMembers();
            }
        }
    }
}
