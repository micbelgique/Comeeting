namespace Comeeting.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class IsPresent : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Coworkers", "IsPresent", c => c.Boolean(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.Coworkers", "IsPresent");
        }
    }
}
