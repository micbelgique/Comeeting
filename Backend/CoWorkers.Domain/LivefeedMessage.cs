using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Comeeting.Domain
{
    public class LivefeedMessage
    {
        public LivefeedMessage()
        {
        }
        public LivefeedMessage(LivefeedMessageType type)
        {
            this.Type = type;
        }

        public Guid Id { get; set; }
        public string Title { get; set; }
        public string Text { get; set; }
        public LivefeedMessageType Type { get; set; }
        public DateTime DateTime { get; set; }
        
        public string CoworkerLinkedInId { get; set; }
        public bool? IsBirthday { get; set; }
        public string PictureUrl { get; set; }

        public string TweetLink { get; set; }

        public Guid CoworkspaceId { get; set; }

        public virtual Coworkspace Coworkspace { get; set; }
    }
}
