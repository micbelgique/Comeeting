namespace Comeeting.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddedTitleToLivefeedMessage : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.LivefeedMessages", "Title", c => c.String());
            AlterColumn("dbo.LivefeedMessages", "IsBirthday", c => c.Boolean());
        }
        
        public override void Down()
        {
            AlterColumn("dbo.LivefeedMessages", "IsBirthday", c => c.Boolean(nullable: false));
            DropColumn("dbo.LivefeedMessages", "Title");
        }
    }
}
