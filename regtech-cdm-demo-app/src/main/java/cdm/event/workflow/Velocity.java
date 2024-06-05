package cdm.event.workflow;

import cdm.base.datetime.PeriodTimeEnum;
import cdm.event.workflow.Velocity;
import cdm.event.workflow.Velocity.VelocityBuilder;
import cdm.event.workflow.Velocity.VelocityBuilderImpl;
import cdm.event.workflow.Velocity.VelocityImpl;
import cdm.event.workflow.meta.VelocityMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="Velocity", builder=Velocity.VelocityBuilderImpl.class, version="${project.version}")
public interface Velocity extends RosettaModelObject {

	VelocityMeta metaData = new VelocityMeta();

	/*********************** Getter Methods  ***********************/
	Integer getPeriodMultiplier();
	PeriodTimeEnum getPeriod();

	/*********************** Build Methods  ***********************/
	Velocity build();
	
	Velocity.VelocityBuilder toBuilder();
	
	static Velocity.VelocityBuilder builder() {
		return new Velocity.VelocityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Velocity> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Velocity> getType() {
		return Velocity.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
		processor.processBasic(path.newSubPath("period"), PeriodTimeEnum.class, getPeriod(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface VelocityBuilder extends Velocity, RosettaModelObjectBuilder {
		Velocity.VelocityBuilder setPeriodMultiplier(Integer periodMultiplier);
		Velocity.VelocityBuilder setPeriod(PeriodTimeEnum period);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
			processor.processBasic(path.newSubPath("period"), PeriodTimeEnum.class, getPeriod(), this);
		}
		

		Velocity.VelocityBuilder prune();
	}

	/*********************** Immutable Implementation of Velocity  ***********************/
	class VelocityImpl implements Velocity {
		private final Integer periodMultiplier;
		private final PeriodTimeEnum period;
		
		protected VelocityImpl(Velocity.VelocityBuilder builder) {
			this.periodMultiplier = builder.getPeriodMultiplier();
			this.period = builder.getPeriod();
		}
		
		@Override
		@RosettaAttribute("periodMultiplier")
		public Integer getPeriodMultiplier() {
			return periodMultiplier;
		}
		
		@Override
		@RosettaAttribute("period")
		public PeriodTimeEnum getPeriod() {
			return period;
		}
		
		@Override
		public Velocity build() {
			return this;
		}
		
		@Override
		public Velocity.VelocityBuilder toBuilder() {
			Velocity.VelocityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Velocity.VelocityBuilder builder) {
			ofNullable(getPeriodMultiplier()).ifPresent(builder::setPeriodMultiplier);
			ofNullable(getPeriod()).ifPresent(builder::setPeriod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Velocity _that = getType().cast(o);
		
			if (!Objects.equals(periodMultiplier, _that.getPeriodMultiplier())) return false;
			if (!Objects.equals(period, _that.getPeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (periodMultiplier != null ? periodMultiplier.hashCode() : 0);
			_result = 31 * _result + (period != null ? period.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Velocity {" +
				"periodMultiplier=" + this.periodMultiplier + ", " +
				"period=" + this.period +
			'}';
		}
	}

	/*********************** Builder Implementation of Velocity  ***********************/
	class VelocityBuilderImpl implements Velocity.VelocityBuilder {
	
		protected Integer periodMultiplier;
		protected PeriodTimeEnum period;
	
		public VelocityBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("periodMultiplier")
		public Integer getPeriodMultiplier() {
			return periodMultiplier;
		}
		
		@Override
		@RosettaAttribute("period")
		public PeriodTimeEnum getPeriod() {
			return period;
		}
		
	
		@Override
		@RosettaAttribute("periodMultiplier")
		public Velocity.VelocityBuilder setPeriodMultiplier(Integer periodMultiplier) {
			this.periodMultiplier = periodMultiplier==null?null:periodMultiplier;
			return this;
		}
		@Override
		@RosettaAttribute("period")
		public Velocity.VelocityBuilder setPeriod(PeriodTimeEnum period) {
			this.period = period==null?null:period;
			return this;
		}
		
		@Override
		public Velocity build() {
			return new Velocity.VelocityImpl(this);
		}
		
		@Override
		public Velocity.VelocityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Velocity.VelocityBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPeriodMultiplier()!=null) return true;
			if (getPeriod()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Velocity.VelocityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Velocity.VelocityBuilder o = (Velocity.VelocityBuilder) other;
			
			
			merger.mergeBasic(getPeriodMultiplier(), o.getPeriodMultiplier(), this::setPeriodMultiplier);
			merger.mergeBasic(getPeriod(), o.getPeriod(), this::setPeriod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Velocity _that = getType().cast(o);
		
			if (!Objects.equals(periodMultiplier, _that.getPeriodMultiplier())) return false;
			if (!Objects.equals(period, _that.getPeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (periodMultiplier != null ? periodMultiplier.hashCode() : 0);
			_result = 31 * _result + (period != null ? period.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "VelocityBuilder {" +
				"periodMultiplier=" + this.periodMultiplier + ", " +
				"period=" + this.period +
			'}';
		}
	}
}
