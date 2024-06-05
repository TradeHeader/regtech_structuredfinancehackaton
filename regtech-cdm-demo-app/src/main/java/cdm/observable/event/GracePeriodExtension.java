package cdm.observable.event;

import cdm.base.datetime.Offset;
import cdm.observable.event.GracePeriodExtension;
import cdm.observable.event.GracePeriodExtension.GracePeriodExtensionBuilder;
import cdm.observable.event.GracePeriodExtension.GracePeriodExtensionBuilderImpl;
import cdm.observable.event.GracePeriodExtension.GracePeriodExtensionImpl;
import cdm.observable.event.meta.GracePeriodExtensionMeta;
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
@RosettaDataType(value="GracePeriodExtension", builder=GracePeriodExtension.GracePeriodExtensionBuilderImpl.class, version="${project.version}")
public interface GracePeriodExtension extends RosettaModelObject {

	GracePeriodExtensionMeta metaData = new GracePeriodExtensionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates whether the grace period extension provision is applicable.
	 */
	Boolean getApplicable();
	/**
	 * The number of calendar or business days after any due date that the reference entity has to fulfil its obligations before a failure to pay credit event is deemed to have occurred. ISDA 2003 Term: Grace Period.
	 */
	Offset getGracePeriod();

	/*********************** Build Methods  ***********************/
	GracePeriodExtension build();
	
	GracePeriodExtension.GracePeriodExtensionBuilder toBuilder();
	
	static GracePeriodExtension.GracePeriodExtensionBuilder builder() {
		return new GracePeriodExtension.GracePeriodExtensionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends GracePeriodExtension> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends GracePeriodExtension> getType() {
		return GracePeriodExtension.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
		processRosetta(path.newSubPath("gracePeriod"), processor, Offset.class, getGracePeriod());
	}
	

	/*********************** Builder Interface  ***********************/
	interface GracePeriodExtensionBuilder extends GracePeriodExtension, RosettaModelObjectBuilder {
		Offset.OffsetBuilder getOrCreateGracePeriod();
		Offset.OffsetBuilder getGracePeriod();
		GracePeriodExtension.GracePeriodExtensionBuilder setApplicable(Boolean applicable);
		GracePeriodExtension.GracePeriodExtensionBuilder setGracePeriod(Offset gracePeriod);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
			processRosetta(path.newSubPath("gracePeriod"), processor, Offset.OffsetBuilder.class, getGracePeriod());
		}
		

		GracePeriodExtension.GracePeriodExtensionBuilder prune();
	}

	/*********************** Immutable Implementation of GracePeriodExtension  ***********************/
	class GracePeriodExtensionImpl implements GracePeriodExtension {
		private final Boolean applicable;
		private final Offset gracePeriod;
		
		protected GracePeriodExtensionImpl(GracePeriodExtension.GracePeriodExtensionBuilder builder) {
			this.applicable = builder.getApplicable();
			this.gracePeriod = ofNullable(builder.getGracePeriod()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("gracePeriod")
		public Offset getGracePeriod() {
			return gracePeriod;
		}
		
		@Override
		public GracePeriodExtension build() {
			return this;
		}
		
		@Override
		public GracePeriodExtension.GracePeriodExtensionBuilder toBuilder() {
			GracePeriodExtension.GracePeriodExtensionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(GracePeriodExtension.GracePeriodExtensionBuilder builder) {
			ofNullable(getApplicable()).ifPresent(builder::setApplicable);
			ofNullable(getGracePeriod()).ifPresent(builder::setGracePeriod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			GracePeriodExtension _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(gracePeriod, _that.getGracePeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (gracePeriod != null ? gracePeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "GracePeriodExtension {" +
				"applicable=" + this.applicable + ", " +
				"gracePeriod=" + this.gracePeriod +
			'}';
		}
	}

	/*********************** Builder Implementation of GracePeriodExtension  ***********************/
	class GracePeriodExtensionBuilderImpl implements GracePeriodExtension.GracePeriodExtensionBuilder {
	
		protected Boolean applicable;
		protected Offset.OffsetBuilder gracePeriod;
	
		public GracePeriodExtensionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("gracePeriod")
		public Offset.OffsetBuilder getGracePeriod() {
			return gracePeriod;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateGracePeriod() {
			Offset.OffsetBuilder result;
			if (gracePeriod!=null) {
				result = gracePeriod;
			}
			else {
				result = gracePeriod = Offset.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("applicable")
		public GracePeriodExtension.GracePeriodExtensionBuilder setApplicable(Boolean applicable) {
			this.applicable = applicable==null?null:applicable;
			return this;
		}
		@Override
		@RosettaAttribute("gracePeriod")
		public GracePeriodExtension.GracePeriodExtensionBuilder setGracePeriod(Offset gracePeriod) {
			this.gracePeriod = gracePeriod==null?null:gracePeriod.toBuilder();
			return this;
		}
		
		@Override
		public GracePeriodExtension build() {
			return new GracePeriodExtension.GracePeriodExtensionImpl(this);
		}
		
		@Override
		public GracePeriodExtension.GracePeriodExtensionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public GracePeriodExtension.GracePeriodExtensionBuilder prune() {
			if (gracePeriod!=null && !gracePeriod.prune().hasData()) gracePeriod = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getApplicable()!=null) return true;
			if (getGracePeriod()!=null && getGracePeriod().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public GracePeriodExtension.GracePeriodExtensionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			GracePeriodExtension.GracePeriodExtensionBuilder o = (GracePeriodExtension.GracePeriodExtensionBuilder) other;
			
			merger.mergeRosetta(getGracePeriod(), o.getGracePeriod(), this::setGracePeriod);
			
			merger.mergeBasic(getApplicable(), o.getApplicable(), this::setApplicable);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			GracePeriodExtension _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(gracePeriod, _that.getGracePeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (gracePeriod != null ? gracePeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "GracePeriodExtensionBuilder {" +
				"applicable=" + this.applicable + ", " +
				"gracePeriod=" + this.gracePeriod +
			'}';
		}
	}
}
