package cdm.product.template;

import cdm.product.template.Duration;
import cdm.product.template.Duration.DurationBuilder;
import cdm.product.template.Duration.DurationBuilderImpl;
import cdm.product.template.Duration.DurationImpl;
import cdm.product.template.DurationTypeEnum;
import cdm.product.template.EvergreenProvision;
import cdm.product.template.meta.DurationMeta;
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
 * Specifies the Duration Terms of the Security Financing Transaction, and optionally any Evergreen terms.
 * @version ${project.version}
 */
@RosettaDataType(value="Duration", builder=Duration.DurationBuilderImpl.class, version="${project.version}")
public interface Duration extends RosettaModelObject {

	DurationMeta metaData = new DurationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the Duration Terms of the Security Financing transaction. e.g. Open or Term.
	 */
	DurationTypeEnum getDurationType();
	/**
	 * A data defining: the right of a party to exercise an Evergreen option
	 */
	EvergreenProvision getEvergreenProvision();

	/*********************** Build Methods  ***********************/
	Duration build();
	
	Duration.DurationBuilder toBuilder();
	
	static Duration.DurationBuilder builder() {
		return new Duration.DurationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Duration> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Duration> getType() {
		return Duration.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("durationType"), DurationTypeEnum.class, getDurationType(), this);
		processRosetta(path.newSubPath("evergreenProvision"), processor, EvergreenProvision.class, getEvergreenProvision());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DurationBuilder extends Duration, RosettaModelObjectBuilder {
		EvergreenProvision.EvergreenProvisionBuilder getOrCreateEvergreenProvision();
		EvergreenProvision.EvergreenProvisionBuilder getEvergreenProvision();
		Duration.DurationBuilder setDurationType(DurationTypeEnum durationType);
		Duration.DurationBuilder setEvergreenProvision(EvergreenProvision evergreenProvision);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("durationType"), DurationTypeEnum.class, getDurationType(), this);
			processRosetta(path.newSubPath("evergreenProvision"), processor, EvergreenProvision.EvergreenProvisionBuilder.class, getEvergreenProvision());
		}
		

		Duration.DurationBuilder prune();
	}

	/*********************** Immutable Implementation of Duration  ***********************/
	class DurationImpl implements Duration {
		private final DurationTypeEnum durationType;
		private final EvergreenProvision evergreenProvision;
		
		protected DurationImpl(Duration.DurationBuilder builder) {
			this.durationType = builder.getDurationType();
			this.evergreenProvision = ofNullable(builder.getEvergreenProvision()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("durationType")
		public DurationTypeEnum getDurationType() {
			return durationType;
		}
		
		@Override
		@RosettaAttribute("evergreenProvision")
		public EvergreenProvision getEvergreenProvision() {
			return evergreenProvision;
		}
		
		@Override
		public Duration build() {
			return this;
		}
		
		@Override
		public Duration.DurationBuilder toBuilder() {
			Duration.DurationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Duration.DurationBuilder builder) {
			ofNullable(getDurationType()).ifPresent(builder::setDurationType);
			ofNullable(getEvergreenProvision()).ifPresent(builder::setEvergreenProvision);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Duration _that = getType().cast(o);
		
			if (!Objects.equals(durationType, _that.getDurationType())) return false;
			if (!Objects.equals(evergreenProvision, _that.getEvergreenProvision())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (durationType != null ? durationType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (evergreenProvision != null ? evergreenProvision.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Duration {" +
				"durationType=" + this.durationType + ", " +
				"evergreenProvision=" + this.evergreenProvision +
			'}';
		}
	}

	/*********************** Builder Implementation of Duration  ***********************/
	class DurationBuilderImpl implements Duration.DurationBuilder {
	
		protected DurationTypeEnum durationType;
		protected EvergreenProvision.EvergreenProvisionBuilder evergreenProvision;
	
		public DurationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("durationType")
		public DurationTypeEnum getDurationType() {
			return durationType;
		}
		
		@Override
		@RosettaAttribute("evergreenProvision")
		public EvergreenProvision.EvergreenProvisionBuilder getEvergreenProvision() {
			return evergreenProvision;
		}
		
		@Override
		public EvergreenProvision.EvergreenProvisionBuilder getOrCreateEvergreenProvision() {
			EvergreenProvision.EvergreenProvisionBuilder result;
			if (evergreenProvision!=null) {
				result = evergreenProvision;
			}
			else {
				result = evergreenProvision = EvergreenProvision.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("durationType")
		public Duration.DurationBuilder setDurationType(DurationTypeEnum durationType) {
			this.durationType = durationType==null?null:durationType;
			return this;
		}
		@Override
		@RosettaAttribute("evergreenProvision")
		public Duration.DurationBuilder setEvergreenProvision(EvergreenProvision evergreenProvision) {
			this.evergreenProvision = evergreenProvision==null?null:evergreenProvision.toBuilder();
			return this;
		}
		
		@Override
		public Duration build() {
			return new Duration.DurationImpl(this);
		}
		
		@Override
		public Duration.DurationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Duration.DurationBuilder prune() {
			if (evergreenProvision!=null && !evergreenProvision.prune().hasData()) evergreenProvision = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDurationType()!=null) return true;
			if (getEvergreenProvision()!=null && getEvergreenProvision().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Duration.DurationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Duration.DurationBuilder o = (Duration.DurationBuilder) other;
			
			merger.mergeRosetta(getEvergreenProvision(), o.getEvergreenProvision(), this::setEvergreenProvision);
			
			merger.mergeBasic(getDurationType(), o.getDurationType(), this::setDurationType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Duration _that = getType().cast(o);
		
			if (!Objects.equals(durationType, _that.getDurationType())) return false;
			if (!Objects.equals(evergreenProvision, _that.getEvergreenProvision())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (durationType != null ? durationType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (evergreenProvision != null ? evergreenProvision.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DurationBuilder {" +
				"durationType=" + this.durationType + ", " +
				"evergreenProvision=" + this.evergreenProvision +
			'}';
		}
	}
}
