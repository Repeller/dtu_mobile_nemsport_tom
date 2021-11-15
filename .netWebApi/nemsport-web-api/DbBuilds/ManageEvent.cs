using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;
using nemsport_web_api.Models;

namespace nemsport_web_api.DbBuilds
{
    public class ManageEvent
    {
        // private string _jsonString = System.IO.File.ReadAllText("loginInfo.json");



        // fields
        private const string _connectionString = "Server=tcp:nemsport-server.database.windows.net,1433; " +
                                                "Initial Catalog=nemsport_db; " +
                                                "Persist Security Info=False;User " +
                                                "ID=nemsport-admin; " +
                                               "Password=Fodbold#C3; " +
                                                "MultipleActiveResultSets=False; " +
                                                "Encrypt=True;TrustServerCertificate=False; " +
                                                "Connection Timeout=30;";

        // normal sql strings
        private const string GET_ALL = "select * from dbo.Event";
        private const string GET_ONE = "select * from dbo.Event WHERE id = @ID";
        private const string POST_ONE = @"insert into dbo.Event 
                                        (created, title, description, eventStart, fk_time_id, maxPlayers, onlyPaid, fk_member_id) 
                                        VALUES 
                                        (@CREATED, @TITLE, @DESCRIPTION, @EVENTSTART, @FK_TIME_ID, @MAXPLAYERS, @ONLYPAID, @FK_MEMBER_ID) ";
        private const string PUT_ONE = @"UPDATE dbo.Event SET 
                                                created = @CREATED, 
                                                title = @TITLE, 
                                                description = @DESCRIPTION, 
                                                eventStart = @EVENTSTART, 
                                                fk_time_id = @FK_TIME_ID, 
                                                maxPlayers = @MAXPLAYERS, 
                                                onlyPaid = @ONLYPAID, 
                                                fk_member_id = @FK_MEMBER_ID 
                                                WHERE id = @ID";
        private const string DELETE_ONE = "DELETE FROM dbo.Event WHERE id = @ID";

        // custom sql strings
        private const string GET_MINE = "select * from dbo.Event " +
                                        "FULL JOIN dbo.EventMember ON dbo.Event.id = dbo.EventMember.fk_event_id " +
                                        "WHERE dbo.Event.fk_member_id = @ID " +
                                        "OR dbo.eventMember.fk_member_id = @ID";

        private const string GET_ALL_PUBLIC = "select * from dbo.Event WHERE dbo.Event.onlyPaid = 0";

        private const string GET_ONLY_MEMBERS = "select * from dbo.Event WHERE dbo.Event.onlyPaid = 1";


        public IEnumerable<Event> Events { get; set; }

        // maybe this part could be deleted, I will test it later
        public IEnumerable<Event> Get()
        {
            return Events;
        }

        // here we only get the Events, where the user is connected to them
        public IEnumerable<Event> GetMine(int id)
        {
            List<Event> tempEvents = new List<Event>();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(GET_MINE, Conn))
            {
                cmd.Parameters.AddWithValue("@ID", id);
                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Event value = ReadNextElement(reader);
                    if (value.Id == id)
                    {
                        tempEvents.Add(value);
                        break;
                    }
                }
                reader.Close();
                Conn.Close();
            }

            return tempEvents;
        }

        /// <summary>
        /// reads the data from the reader and returns a Event obj
        /// </summary>
        /// <param name="reader">the reader in use</param>
        /// <returns>the Event obj from the reader</returns>
        protected Event ReadNextElement(SqlDataReader reader)
        {
            Event tempEvent = new Event();

            tempEvent.Id = reader.GetInt32(0);
            tempEvent.Created = reader.GetDateTime(1);
            tempEvent.Title = reader.GetString(2);
            tempEvent.Descript = reader.GetString(3);
            tempEvent.EventStart = reader.GetDateTime(4);
            tempEvent.OnlyPaid = reader.GetInt32(5);
            tempEvent.MaxPlayers = reader.GetInt32(6);
            tempEvent.Fk_member_id = reader.GetInt32(7);

            return tempEvent;
        }

        /// <summary>
        /// used to load everything into the list
        /// </summary>
        /// <returns>the list of all Event</returns>
        public IEnumerable<Event> LoadEvents()
        {
            List<Event> tempEvents = new List<Event>();
            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(GET_ALL, Conn))
            {
                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Event value = ReadNextElement(reader);
                    tempEvents.Add(value);
                }
                reader.Close();
                Conn.Close();
            }

            return tempEvents;
        }

        public Event Get(int id)
        {
            Event tempEvent = new Event();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(GET_ONE, Conn))
            {
                cmd.Parameters.AddWithValue("@ID", id);
                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Event value = ReadNextElement(reader);
                    if (value.Id == id)
                    {
                        tempEvent = value;
                        break;
                    }
                }
                reader.Close();
                Conn.Close();
            }

            return tempEvent;
        }

        public void Post(Event value)
        {
            Event tempEvent = new Event();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(POST_ONE, Conn))
            {

                //(@ID, @CREATED, @TITLE, @DESCRIPTION, @EVENTSTART, @FK_TIME_ID, @MAXPLAYERS, @ONLYPAID, @FK_MEMBER_ID)

                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@CREATED", value.Created);
                cmd.Parameters.AddWithValue("@TITLE", value.Title);
                cmd.Parameters.AddWithValue("@DESCRIPTION", value.Descript);
                cmd.Parameters.AddWithValue("@EVENTSTART", value.EventStart);
                cmd.Parameters.AddWithValue("@FK_TIME_ID", value.Fk_member_id);
                cmd.Parameters.AddWithValue("@MAXPLAYERS", value.MaxPlayers);
                cmd.Parameters.AddWithValue("@ONLYPAID", value.OnlyPaid);
                cmd.Parameters.AddWithValue("@FK_MEMBER_ID", value.Fk_member_id);


                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempEvent = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                Events = LoadEvents();
            }
        }

        public void Put(int id, Event value)
        {
            Event tempEvent = new Event();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(PUT_ONE, Conn))
            {
                // (@ID, @CREATED, @TITLE, @DESCRIPTION, @EVENTSTART, @FK_TIME_ID, @MAXPLAYERS, @ONLYPAID, @FK_MEMBER_ID)
                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@ID", id);
                cmd.Parameters.AddWithValue("@CREATED", value.Created);
                cmd.Parameters.AddWithValue("@TITLE", value.Title);
                cmd.Parameters.AddWithValue("@DESCRIPTION", value.Descript);
                cmd.Parameters.AddWithValue("@EVENTSTART", value.EventStart);
                cmd.Parameters.AddWithValue("@FK_TIME_ID", value.Fk_member_id);
                cmd.Parameters.AddWithValue("@MAXPLAYERS", value.MaxPlayers);
                cmd.Parameters.AddWithValue("@ONLYPAID", value.OnlyPaid);
                cmd.Parameters.AddWithValue("@FK_MEMBER_ID", value.Fk_member_id);

                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempEvent = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                Events = LoadEvents();
            }
        }

        public void Delete(int id)
        {
            Event tempEvent = new Event();

            using (SqlConnection Conn = new SqlConnection(_connectionString))
            using (SqlCommand cmd = new SqlCommand(DELETE_ONE, Conn))
            {
                // we will not use the id, since that get created in the DB
                cmd.Parameters.AddWithValue("@ID", id);

                Conn.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    tempEvent = ReadNextElement(reader);
                }
                reader.Close();
                Conn.Close();

                // TODO : maybe remove this line
                Events = LoadEvents();
            }
        }
    }
}
