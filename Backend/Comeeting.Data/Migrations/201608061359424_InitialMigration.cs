namespace Comeeting.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class InitialMigration : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Coworkers",
                c => new
                    {
                        LinkedInId = c.String(nullable: false, maxLength: 128),
                        FirstName = c.String(),
                        LastName = c.String(),
                        PictureUrl = c.String(),
                        Summary = c.String(),
                        CurrentCoworkspaceId = c.Guid(),
                    })
                .PrimaryKey(t => t.LinkedInId)
                .ForeignKey("dbo.Coworkspaces", t => t.CurrentCoworkspaceId)
                .Index(t => t.CurrentCoworkspaceId);
            
            CreateTable(
                "dbo.Coworkspaces",
                c => new
                    {
                        Id = c.Guid(nullable: false),
                        Name = c.String(),
                        PictureUrl = c.String(),
                        Description = c.String(),
                        Address = c.String(),
                        ZipCode = c.String(),
                        City = c.String(),
                        GeolocationLongitude = c.Double(nullable: false),
                        GeolocationLatitude = c.Double(nullable: false),
                        GeofencingRadius = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.LivefeedMessages",
                c => new
                    {
                        Id = c.Guid(nullable: false),
                        Text = c.String(),
                        Type = c.Int(nullable: false),
                        DateTime = c.DateTime(nullable: false),
                        CoworkerLinkedInId = c.String(maxLength: 128),
                        IsBirthday = c.Boolean(nullable: false),
                        PictureUrl = c.String(),
                        TweetLink = c.String(),
                        Sender = c.String(),
                        CoworkspaceId = c.Guid(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Coworkspaces", t => t.CoworkspaceId, cascadeDelete: true)
                .ForeignKey("dbo.Coworkers", t => t.CoworkerLinkedInId)
                .Index(t => t.CoworkerLinkedInId)
                .Index(t => t.CoworkspaceId);
            
            CreateTable(
                "dbo.Positions",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        CompanyName = c.String(),
                        IsCurrent = c.Boolean(nullable: false),
                        StartDate = c.DateTime(nullable: false),
                        EndDate = c.DateTime(),
                        Title = c.String(),
                        CoworkerLinkedInId = c.String(maxLength: 128),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Coworkers", t => t.CoworkerLinkedInId)
                .Index(t => t.CoworkerLinkedInId);
            
            CreateTable(
                "dbo.Favorites",
                c => new
                    {
                        CoworkerId = c.String(nullable: false, maxLength: 128),
                        CoworkspaceId = c.Guid(nullable: false),
                    })
                .PrimaryKey(t => new { t.CoworkerId, t.CoworkspaceId })
                .ForeignKey("dbo.Coworkers", t => t.CoworkerId, cascadeDelete: true)
                .ForeignKey("dbo.Coworkspaces", t => t.CoworkspaceId, cascadeDelete: true)
                .Index(t => t.CoworkerId)
                .Index(t => t.CoworkspaceId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Positions", "CoworkerLinkedInId", "dbo.Coworkers");
            DropForeignKey("dbo.LivefeedMessages", "CoworkerLinkedInId", "dbo.Coworkers");
            DropForeignKey("dbo.Favorites", "CoworkspaceId", "dbo.Coworkspaces");
            DropForeignKey("dbo.Favorites", "CoworkerId", "dbo.Coworkers");
            DropForeignKey("dbo.LivefeedMessages", "CoworkspaceId", "dbo.Coworkspaces");
            DropForeignKey("dbo.Coworkers", "CurrentCoworkspaceId", "dbo.Coworkspaces");
            DropIndex("dbo.Favorites", new[] { "CoworkspaceId" });
            DropIndex("dbo.Favorites", new[] { "CoworkerId" });
            DropIndex("dbo.Positions", new[] { "CoworkerLinkedInId" });
            DropIndex("dbo.LivefeedMessages", new[] { "CoworkspaceId" });
            DropIndex("dbo.LivefeedMessages", new[] { "CoworkerLinkedInId" });
            DropIndex("dbo.Coworkers", new[] { "CurrentCoworkspaceId" });
            DropTable("dbo.Favorites");
            DropTable("dbo.Positions");
            DropTable("dbo.LivefeedMessages");
            DropTable("dbo.Coworkspaces");
            DropTable("dbo.Coworkers");
        }
    }
}
