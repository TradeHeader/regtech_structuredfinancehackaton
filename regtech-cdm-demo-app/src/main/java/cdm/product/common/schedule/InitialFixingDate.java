package cdm.product.common.schedule;

import cdm.base.datetime.RelativeDateOffset;
import cdm.product.common.schedule.InitialFixingDate;
import cdm.product.common.schedule.InitialFixingDate.InitialFixingDateBuilder;
import cdm.product.common.schedule.InitialFixingDate.InitialFixingDateBuilderImpl;
import cdm.product.common.schedule.InitialFixingDate.InitialFixingDateImpl;
import cdm.product.common.schedule.meta.InitialFixingDateMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A CDM class which purpose is to specify the initial fixing date either alongside the FpML interest rate specification as an offset of another date, or alongside the credit derivative specification as an unadjusted date.
 * @version ${project.version}
 */
@RosettaDataType(value="InitialFixingDate", builder=InitialFixingDate.InitialFixingDateBuilderImpl.class, version="${project.version}")
public interface InitialFixingDate extends RosettaModelObject {

	InitialFixingDateMeta metaData = new InitialFixingDateMeta();

	/*********************** Getter Methods  ***********************/
	RelativeDateOffset getRelativeDateOffset();
	Date getInitialFixingDate();

	/*********************** Build Methods  ***********************/
	InitialFixingDate build();
	
	InitialFixingDate.InitialFixingDateBuilder toBuilder();
	
	static InitialFixingDate.InitialFixingDateBuilder builder() {
		return new InitialFixingDate.InitialFixingDateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends InitialFixingDate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends InitialFixingDate> getType() {
		return InitialFixingDate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("relativeDateOffset"), processor, RelativeDateOffset.class, getRelativeDateOffset());
		processor.processBasic(path.newSubPath("initialFixingDate"), Date.class, getInitialFixingDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface InitialFixingDateBuilder extends InitialFixingDate, RosettaModelObjectBuilder {
		RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateRelativeDateOffset();
		RelativeDateOffset.RelativeDateOffsetBuilder getRelativeDateOffset();
		InitialFixingDate.InitialFixingDateBuilder setRelativeDateOffset(RelativeDateOffset relativeDateOffset);
		InitialFixingDate.InitialFixingDateBuilder setInitialFixingDate(Date initialFixingDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("relativeDateOffset"), processor, RelativeDateOffset.RelativeDateOffsetBuilder.class, getRelativeDateOffset());
			processor.processBasic(path.newSubPath("initialFixingDate"), Date.class, getInitialFixingDate(), this);
		}
		

		InitialFixingDate.InitialFixingDateBuilder prune();
	}

	/*********************** Immutable Implementation of InitialFixingDate  ***********************/
	class InitialFixingDateImpl implements InitialFixingDate {
		private final RelativeDateOffset relativeDateOffset;
		private final Date initialFixingDate;
		
		protected InitialFixingDateImpl(InitialFixingDate.InitialFixingDateBuilder builder) {
			this.relativeDateOffset = ofNullable(builder.getRelativeDateOffset()).map(f->f.build()).orElse(null);
			this.initialFixingDate = builder.getInitialFixingDate();
		}
		
		@Override
		@RosettaAttribute("relativeDateOffset")
		public RelativeDateOffset getRelativeDateOffset() {
			return relativeDateOffset;
		}
		
		@Override
		@RosettaAttribute("initialFixingDate")
		public Date getInitialFixingDate() {
			return initialFixingDate;
		}
		
		@Override
		public InitialFixingDate build() {
			return this;
		}
		
		@Override
		public InitialFixingDate.InitialFixingDateBuilder toBuilder() {
			InitialFixingDate.InitialFixingDateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(InitialFixingDate.InitialFixingDateBuilder builder) {
			ofNullable(getRelativeDateOffset()).ifPresent(builder::setRelativeDateOffset);
			ofNullable(getInitialFixingDate()).ifPresent(builder::setInitialFixingDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InitialFixingDate _that = getType().cast(o);
		
			if (!Objects.equals(relativeDateOffset, _that.getRelativeDateOffset())) return false;
			if (!Objects.equals(initialFixingDate, _that.getInitialFixingDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (relativeDateOffset != null ? relativeDateOffset.hashCode() : 0);
			_result = 31 * _result + (initialFixingDate != null ? initialFixingDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InitialFixingDate {" +
				"relativeDateOffset=" + this.relativeDateOffset + ", " +
				"initialFixingDate=" + this.initialFixingDate +
			'}';
		}
	}

	/*********************** Builder Implementation of InitialFixingDate  ***********************/
	class InitialFixingDateBuilderImpl implements InitialFixingDate.InitialFixingDateBuilder {
	
		protected RelativeDateOffset.RelativeDateOffsetBuilder relativeDateOffset;
		protected Date initialFixingDate;
	
		public InitialFixingDateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("relativeDateOffset")
		public RelativeDateOffset.RelativeDateOffsetBuilder getRelativeDateOffset() {
			return relativeDateOffset;
		}
		
		@Override
		public RelativeDateOffset.RelativeDateOffsetBuilder getOrCreateRelativeDateOffset() {
			RelativeDateOffset.RelativeDateOffsetBuilder result;
			if (relativeDateOffset!=null) {
				result = relativeDateOffset;
			}
			else {
				result = relativeDateOffset = RelativeDateOffset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("initialFixingDate")
		public Date getInitialFixingDate() {
			return initialFixingDate;
		}
		
	
		@Override
		@RosettaAttribute("relativeDateOffset")
		public InitialFixingDate.InitialFixingDateBuilder setRelativeDateOffset(RelativeDateOffset relativeDateOffset) {
			this.relativeDateOffset = relativeDateOffset==null?null:relativeDateOffset.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("initialFixingDate")
		public InitialFixingDate.InitialFixingDateBuilder setInitialFixingDate(Date initialFixingDate) {
			this.initialFixingDate = initialFixingDate==null?null:initialFixingDate;
			return this;
		}
		
		@Override
		public InitialFixingDate build() {
			return new InitialFixingDate.InitialFixingDateImpl(this);
		}
		
		@Override
		public InitialFixingDate.InitialFixingDateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InitialFixingDate.InitialFixingDateBuilder prune() {
			if (relativeDateOffset!=null && !relativeDateOffset.prune().hasData()) relativeDateOffset = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getRelativeDateOffset()!=null && getRelativeDateOffset().hasData()) return true;
			if (getInitialFixingDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InitialFixingDate.InitialFixingDateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			InitialFixingDate.InitialFixingDateBuilder o = (InitialFixingDate.InitialFixingDateBuilder) other;
			
			merger.mergeRosetta(getRelativeDateOffset(), o.getRelativeDateOffset(), this::setRelativeDateOffset);
			
			merger.mergeBasic(getInitialFixingDate(), o.getInitialFixingDate(), this::setInitialFixingDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InitialFixingDate _that = getType().cast(o);
		
			if (!Objects.equals(relativeDateOffset, _that.getRelativeDateOffset())) return false;
			if (!Objects.equals(initialFixingDate, _that.getInitialFixingDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (relativeDateOffset != null ? relativeDateOffset.hashCode() : 0);
			_result = 31 * _result + (initialFixingDate != null ? initialFixingDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InitialFixingDateBuilder {" +
				"relativeDateOffset=" + this.relativeDateOffset + ", " +
				"initialFixingDate=" + this.initialFixingDate +
			'}';
		}
	}
}
