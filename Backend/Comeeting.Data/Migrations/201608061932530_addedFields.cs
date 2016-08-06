namespace Comeeting.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class addedFields : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.LivefeedMessages", "CoworkerLinkedInId", "dbo.Coworkers");
            DropIndex("dbo.LivefeedMessages", new[] { "CoworkerLinkedInId" });
            AddColumn("dbo.Coworkers", "Headline", c => c.String());
            AlterColumn("dbo.LivefeedMessages", "CoworkerLinkedInId", c => c.String());
        }
        
        public override void Down()
        {
            AlterColumn("dbo.LivefeedMessages", "CoworkerLinkedInId", c => c.String(maxLength: 128));
            DropColumn("dbo.Coworkers", "Headline");
            CreateIndex("dbo.LivefeedMessages", "CoworkerLinkedInId");
            AddForeignKey("dbo.LivefeedMessages", "CoworkerLinkedInId", "dbo.Coworkers", "LinkedInId");
        }
    }
}
