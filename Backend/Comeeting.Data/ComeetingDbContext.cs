using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Comeeting.Domain;

namespace Comeeting.Data
{
    public class ComeetingDbContext : DbContext
    {
        public ComeetingDbContext() : base("Comeeting")
        {

        }
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            EntityTypeConfiguration<Coworker> coworkerConfig = modelBuilder.Entity<Coworker>();
            EntityTypeConfiguration<Coworkspace> coworkspaceConfig = modelBuilder.Entity<Coworkspace>();
            EntityTypeConfiguration<LivefeedMessage> livefeedConfig = modelBuilder.Entity<LivefeedMessage>();

            coworkerConfig.HasKey(c => c.LinkedInId);
            coworkerConfig.HasMany(c => c.FavoriteCoworkspaces)
                .WithMany(c => c.CoworkerFavorites)
                .Map((config) =>
                    config.MapLeftKey("CoworkerId")
                        .MapRightKey("CoworkspaceId")
                        .ToTable("Favorites")
                );

            coworkspaceConfig.HasKey(c => c.Id);

            livefeedConfig.HasKey(l => l.Id);



            base.OnModelCreating(modelBuilder);
        }

        public DbSet<Coworkspace> Coworkspaces { get; set; }
        public DbSet<Coworker> Coworkers { get; set; }
        public DbSet<LivefeedMessage> LivefeedMessages { get; set; }
        public DbSet<Position> Positions { get; set; }
    }
}
