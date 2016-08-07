namespace Comeeting.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class DeletedSenderFromLivefeed : DbMigration
    {
        public override void Up()
        {
            DropColumn("dbo.LivefeedMessages", "Sender");
        }
        
        public override void Down()
        {
            AddColumn("dbo.LivefeedMessages", "Sender", c => c.String());
        }
    }
}
